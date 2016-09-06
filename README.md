# rest-metapi
Dynamic REST API:
Just create a MongoDB collection and it's all.. you got a RESTful Web Service to manage that collection.
+ Avoid any back-end implementation.
+ Feel free to focus on improving your front-end.
+ No additional commands needed.

#####Project Keywords: `Spring` `MongoDB` `Log4J` `RESTful` `Spring-Security` `OAuth2`

## Service Endpoints
Every RESTful Webservice includes this endpoints:

+ findAll
```java
    GET: /api/{collection}
```

+ getById
```java
    GET: /api/{collection}/{id}
```

+ insert
```java
    POST: /api/{collection}
```

+ query
```java
    POST: /api/{collection}/query
    //REQUIRES JSON Body request with search prototype
```

+ update
```java
    PUT: /api/{collection}/{id}
```

+ delete
```java
    DELETE: /api/{collection}/{id}
```
## OAuth2 Security
This project includes an OAuth2 integration based on [@neel4software](https://github.com/neel4software) [SpringSecurityOAuth2 project](https://github.com/neel4software/SpringSecurityOAuth2).

To use RestFul api endpoints, user must get a token access with `GET` request like that:

```java
    http://localhost:8080/metapi/oauth/token?grant_type=password&client_id=metapi&client_secret=metapi&username=johnsmith&password=oauth2@metapi
```
> Configuration details is located on /WEB-INF/spring-security.xml

Server response an JSON like that:

```json
{
   "value":"016237b3-d31f-4316-894b-50c93c206448",
   "expiration":1473125181711,
   "tokenType":"bearer",
   "refreshToken":{
      "value":"f1f2864e-fa8f-491c-af90-0e3453cf753a",
      "expiration":1475717061710
   },
   "scope":[

   ],
   "additionalInformation":{

   },
   "expired":false,
   "expiresIn":119
}
```

Add `access_token` request parameter to API request:

```java
 http://localhost:8080/metapi/api/cities?access_token=1e9fe3f3-42bf-444a-b68c-f29e4afbd309
```

And voil&#224;, now our dynamic api is protected via OAuth2
#####Source: http://www.beingjavaguys.com/2014/10/spring-security-oauth2-integration.html By Nagesh.Chauhan(neel4soft@gmail.com)

## License
The content of this project itself is licensed under the [MIT license](LICENSE.md).
