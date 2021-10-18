# spring-boot-oauth2
A POC to review the different Oauth2 flows

- https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps#web-application-flow

```
mvn clean spring-boot:run -pl base -Dspring-boot.run.arguments="--client_id=MY_KEY --client_secret=MY_KEY"
mvn clean spring-boot:run -pl click -Dspring-boot.run.arguments="--spring.security.oauth2.client.registration.github.client-id=MY_KEY --spring.security.oauth2.client.registration.github.client-secret=MY_KEY"
```