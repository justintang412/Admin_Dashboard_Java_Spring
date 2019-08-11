# springboot_with_admin-sb-ui
Tech Stack: springboot, spring security, thymeleaf, bootstrap, jquery, MyBatis, Solr for searching

For security:
Declared a service and let it implements from @org.springframework.security.core.userdetails.UserDetailsService, then get all the code for permissions of the operations, which are the functions in the Controllers.

On top of the Controller functions, a pre-autherize annotation is used:
@PreAuthorize("hasAuthority('certificationTypes')")
means anyone who have the code 'certificationTypes' can access this function.

For searching via Solr, which is within IndexController.java.
