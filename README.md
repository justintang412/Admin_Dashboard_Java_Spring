# springboot_with_admin-sb-ui
Tech Stack: springboot, spring security, thymeleaf, bootstrap, jquery, MyBatis, Solr for searching

For security:
Declared a service and let it implements from @org.springframework.security.core.userdetails.UserDetailsService, then get all the code for permissions of the operations, which are the functions in the Controllers.

On top of the Controller functions, a pre-autherize annotation is used:
@PreAuthorize("hasAuthority('certificationTypes')")
means anyone who have the code 'certificationTypes' can access this function.

For searching via Solr, which is within IndexController.java:
    HttpSolrClient.Builder builder = new HttpSolrClient.Builder();
		builder.withBaseSolrUrl(env.getProperty("solr.host.bid"));
		HttpSolrClient solrClient = builder.build();

		SolrQuery query = new SolrQuery();
	
		int min = pageParam.getStart();
		int pagesize = pageParam.getPagesize();
		query.setStart(min);
		query.setRows(pagesize);
		
		query.set("q", search);
		
		QueryResponse rsp = null;
		try {
		   rsp = solrClient.query( query );
		} catch (Exception e) {
		   e.printStackTrace();
		}
		SolrDocumentList results = rsp.getResults();
		
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (SolrDocument doc : results) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", doc.getFieldValue("id"));
			BidFile bidFile  = bidFileService.selectBidFileByPath(doc.getFieldValue("id").toString());
			if(bidFile==null)
				continue;
			else {
				map.put("type", bidFile.getDescription());
				map.put("fileId", bidFile.getId());
				map.put("creator", sysUserService.getUserById(bidFile.getUploader()).getSalt());
				map.put("uploadTime", bidFile.getUploadTime());
				map.put("name", bidFile.getName());
			}
			
			rowsList.add(map);
		}
