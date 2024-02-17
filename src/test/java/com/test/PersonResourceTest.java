package com.test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito; // Same import for general & quarkus mockito lib

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


// Split this testing file into
// 1) Integrations tests
// 2) Normal resource test

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
    public void testAddPerson() {
        Person mockPerson = new Person();
        mockPerson.setId(1L);
        mockPerson.setFirstName("John");
        mockPerson.setFirstName("Doe");

//        Mockito.when(personService.getPersonById(1L)).thenReturn(Optional.of(mockPerson));

        Person retrievedPerson = personResource.getPersonById(1L);

        Assertions.assertEquals(mockPerson, retrievedPerson);
    }


}