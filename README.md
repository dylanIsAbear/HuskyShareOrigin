# HuskyShare

## Frontend guide

Frontend: only edit files in `src/main/resources`.


### Important notes
Found any supports files and docs on https://drive.google.com/drive/folders/0ACRawbuRWIiwUk9PVA

### Known bugs
* RedisTemplate cannot be @Autowired
* Redis client cannot be initialized
* Profile entity and ProfileDao cannot be initialized
* Spring mailSender cannot send mail with Error 501

### Directory structure
* `css`, `js`, `img`, etc. - static resource files.
* `templates` - html files. They will be parsed by Thymeleaf (explained below).
* `templates/fragments` - html fragments. Not to be served standalone. Only to be included in other html files.

### APIs used
* RESTFUL structure, found more at https://restfulapi.net/rest-architectural-constraints/
* JSON when dissolving HTTP request/response, JWT library from https://github.com/auth0/java-jwt
* Shiro as Security manager, more about Apache Shiro at https://shiro.apache.org/tutorial.html
* Spring/Spring boot, Spring tutorial https://www.baeldung.com/spring-boot
* Spring data jpa with JDBC & MySQL
* Nginx as cluster, beginner guide of Nginx and cluster at http://nginx.org/en/docs/beginners_guide.html
