# Admin Dashboard Backend & UI Sample (Spring Boot, MyBatis, Solr, Thymeleaf)

This repository demonstrates enterprise web development patterns and integration of modern Java/Spring technologies.  
It features secure authentication, role-based access control, database interaction, and advanced search capabilities in a full-stack setup.

---

## 🛠️ Technologies Demonstrated

### Backend
- **Java 8:** Strongly-typed, object-oriented programming language.
- **Spring Boot:** Simplifies setup and deployment of Spring applications.
- **Spring Security:** Implements secure authentication and role-based access (see custom `UserDetailsService` and use of `@PreAuthorize`).
- **MyBatis:** Lightweight ORM for robust and flexible database access (MySQL).
- **Solr (SolrJ client):** Integrated for advanced search functionality.

### Frontend
- **Thymeleaf:** Server-side Java templating engine for dynamic HTML views.
- **Bootstrap:** Responsive CSS framework for modern, mobile-friendly UIs.
- **jQuery:** Streamlines DOM manipulation and AJAX.

### Additional Tools
- **Spring Boot DevTools:** For rapid development and hot reload.
- **JUnit, Spring Test:** For unit and integration testing.

---

## 🔒 Security Patterns

- Custom login and user management with Spring Security.
- Method-level authorization (e.g., `@PreAuthorize("hasAuthority('certificationTypes')")`).

---

## 🔎 Search Integration

- Solr search logic integrated via SolrJ in the backend (`IndexController.java`).

---

## 📁 Project Structure

- **Backend:** `src/main/java/com/yczx/`
- **Frontend Templates:** `src/main/resources/templates/`
- **Static Assets:** `src/main/resources/static/`

---

## 💡 About Me

I'm a Java developer focused on building robust, secure, and maintainable web applications using the Spring ecosystem.  
Feel free to connect or reach out for job opportunities or collaboration!

- [LinkedIn](https://linkedin.com/in/justintang412)
- [Email](mailto:justintang412@gmail.com)

