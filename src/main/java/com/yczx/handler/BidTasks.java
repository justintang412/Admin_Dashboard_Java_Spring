package com.yczx.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yczx.domain.BidFile;
import com.yczx.service.BidFileService;

@Component
public class BidTasks {
	@Autowired
	private Environment env;
	@Autowired
	BidFileService bidFileService;

	private static CloseableHttpClient httpClient;// = HttpClientBuilder.create().build(); //no timeout handling...
	static {
		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		// 客户端和服务器建立连接的timeout
		requestConfigBuilder.setConnectTimeout(30000);
		// 从连接池获取连接的timeout
		requestConfigBuilder.setConnectionRequestTimeout(30000);
		// 连接建立后，request没有回应的timeout
		requestConfigBuilder.setSocketTimeout(30000);
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
		clientBuilder.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(30000).build()); // 连接建立后，request没有回应的timeout
		clientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
		httpClient = clientBuilder.build();
	}
	private static HttpSolrClient client = null;

	@Scheduled(fixedDelay = 5000)
	public void processFile() {
		if(client ==null) {
			HttpSolrClient.Builder builder = new HttpSolrClient.Builder();
			builder.withBaseSolrUrl(env.getProperty("solr.host.bid"));
			client = builder.build();
		}
	
		
		ArrayList<BidFile> bidFiles = bidFileService.selectUnProcessedBidFiles();
		for (BidFile bidFile : bidFiles) {
			if (bidFile.getDataStatus() == 0) {
				// index the newly uploaded file
				indexFile(bidFile);
				// update the field of data_stauts to 1 after indexed its file
				bidFile.setDataStatus(new Long(1));
				bidFileService.updateBidFileToIndexed(bidFile);
			}
			if (bidFile.getDataStatus() == 2) {
				// remote the index of the to be deleted file
				deleteFileIndex(bidFile);
				// delete the file from disk
				File file = new File(env.getProperty("filePath") + bidFile.getPath());
				if (file.exists()) {
					file.delete();
				}
				// delete the record in the table of bid_file
				bidFileService.deleteBidFile(bidFile.getId());
			}
		}
	}

	private void indexFile(BidFile bidFile) {
		HttpPost httppost = new HttpPost(env.getProperty("solr.host.bid") + "/update/extract");
		FileBody bin = new FileBody(new File(env.getProperty("filePath") + bidFile.getPath()));
		HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin)
				.addPart("literal.id", new StringBody(bidFile.getPath(), ContentType.TEXT_PLAIN))
				.addPart("commit", new StringBody("true", ContentType.TEXT_PLAIN)).build();

		httppost.setEntity(reqEntity);

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			EntityUtils.consume(resEntity);
			response.close();
			response = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void deleteFileIndex(BidFile bidFile) {
		try {
			client.deleteByQuery("id:" + bidFile.getPath());
			client.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}

	}
}
