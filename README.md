# SpringSecurityWithJwt

## About 
 Demo Application for integrating Spring Security with Spring Boot Application, that increments gradually from <strong>Basic Authentication</strong> to <strong>Form Based Authentication</strong> and finally <strong>JWT Authentication</strong>.
 
Follow <a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commits/main">Commit History</a> to understand the incremental changes made in the application.


 
## Concepts Explored
<ul>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commit/d422af271b50c24f66b93786d8a3a5c566316647" />Basic Authentication</li>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commit/2a1b0610f6ec3357b10eebe8e79ac4ef4f4f52ea" />Form Based Authentication</li>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/tree/JwtAuthentication" />JWT Based Authentication</li>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commit/409ea61c6873894a9c7a2fb3fb5ad474f7b107ea" />InMemroy Users Authentication</li>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commit/f96a7235c445420153b4852d2809c60d0f5edcbb" />Database User Authentication</li>
  <li><a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/commit/b3d16877229bbc89f3d20633e32b46775c03c9d0" />Ant Matchers</li>
</ul>
  
## Usage

### Prerequisites
<ul>
  <li>Java 8</li>
 <li>PostgreSQL (Required only if you wish to use Persistent DB. Else Application works fine with H2 in-memory DB)</li>
 </ul>

### Build and Deploy Project
  
<ul>
 <li>Clone <a href="https://github.com/manasacharyya25/SpringSecurityWithJwt/tree/JwtAuthentication">JwtAuthentication Branch</a></li>
 <li>In Root Directory, Execute  <strong><i>gradlew build</i><strong>. This will pull all project dependencies and build the Java Executable</li>
 <li>Once Build Completes, Execute <strong><i>gradlew bootRun</i><strong> to start the application. The application will be hosted at http://localhost:8080</li>
</ul>
  
### Application Details
 <ul>
    <h5>The application by default works with in-memory H2 Database. To change this behaviour and use PostgreSQL, steps to follow:</h5>
     <li>In <strong><i>build.gradle</i></strong> under <strong>dependencies</strong>, comment out </li>

```
      runtimeOnly 'com.h2database:h2'
```

<li>In <strong><i>build.gradle</i></strong> under <strong>dependencies</strong>, uncomment out </li>  

```
	runtimeOnly 'org.postgresql:postgresql'
```
<li> Inclue Database Configuration in <strong><i>application.yml</i><strong></li>

```
	datasource:  
		url: jdbc:postgresql://localhost:5431/<Database Name>  
		username: postgres  
		password: Password
```
      
      
 </ul>
  
### Test using Postman
  Upon Application Statup, the database is seeded with 2 user details. Check <strong>resources/data.sql</strong> for more details.
  <ul>
	  <li><strong>Step 1: Obtain JWT Token</strong></li>
	  Perform POST Request to <a>http://localhost:8080/authenticate</a>. Returns a <i>JWT Token</i>  in Response.
 
 ![image](https://user-images.githubusercontent.com/42498389/115140449-0d419c00-a055-11eb-9bdd-e7003c9f88c2.png)
 
 
<li><strong>Step 2: Access Protected Resource with JWT Token</strong></li>
	Perform GET Request to <a>http://localhost:8080/home</a>. Add JWT Token obtained in previous step, as Bearer Token under Authorization Tab of Postman Request.
 
 ![image](https://user-images.githubusercontent.com/42498389/115140558-83460300-a055-11eb-9054-62a8411e1918.png)
 
 
<li>You should get <strong>200OK</strong> Response, if you provided the correct JWT Token. Else <strong>403 Forbidden</strong> Response.</li>
	 
 
## Author

<strong>Manas Acharyya</strong>

  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.linkedin.com/in/manasacharyya25" target="_blank">linkedin.com/in/manasacharyya25</a></p>
