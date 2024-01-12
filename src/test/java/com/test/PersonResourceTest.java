package com.test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonResourceTest {

    // Test api methods, using Mockito
    @InjectMock
    private PersonResource personResource;

    @Mock
    private Person person;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/v1/hello")// Added api/v1/ to url
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testGetAllPersons() {

    }


}