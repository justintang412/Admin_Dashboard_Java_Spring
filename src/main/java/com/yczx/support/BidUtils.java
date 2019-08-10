package com.yczx.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.yczx.domain.BidFile;
import com.yczx.service.BidFileService;

public class BidUtils {
	public static String getLoginedUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return username;
	}

	public static void downloadBidFile(BidFile bidFile, HttpServletResponse response, String fileDir) {
		response.reset();
		String filename = null;
		try {
			filename = new String(bidFile.getName().getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(filename==null) {
			filename = "附件";
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		File file = new File(fileDir + bidFile.getPath());
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/octet-stream");
		OutputStream ous = null;
		FileInputStream fis = null;
		try {
			ous = response.getOutputStream();
			fis = new FileInputStream(file);
			byte[] buf = new byte[400 * 1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				ous.write(buf, 0, len);
			}
			ous.flush();
			ous.close();
			ous = null;
			fis.close();
			fis = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ous != null) {
				try {
					ous.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static BidFile uploadBidFile(String fileDir, Long uploaderId, MultipartFile file,
			BidFileService bidFileService, String fileDescription) {

		String fileName = file.getOriginalFilename();
		String ext = "";
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex != -1) {
			ext = fileName.substring(dotIndex, fileName.length());
		}
		String uuid = UUID.randomUUID().toString();

		BidFile bidFile = new BidFile();
		bidFile.setPath(uuid + ext);
		bidFile.setName(fileName);
		bidFile.setDescription(fileDescription);
		bidFile.setUploader(uploaderId);
		if (file != null) {
			InputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = file.getInputStream();
				File outputFile = new File(fileDir + uuid + ext);
				fos = new FileOutputStream(outputFile);
				byte[] buf = new byte[400 * 1024];
				int len = 0;
				while ((len = fis.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				fis.close();
				fis = null;
				fos.close();
				fos = null;

				bidFileService.insertBidFile(bidFile);
			} catch (IOException e) {
				e.printStackTrace();
				bidFile = null;
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return bidFile;
	}
}
