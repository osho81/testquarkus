package com.test;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


// Change branch name, master to main, locally

@Path("/api/v1")
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/addperson")
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        System.out.println("In add Person method");
        Person tempPerson = new Person();
        tempPerson.setFirstName(person.getFirstName());
        tempPerson.setLastName(person.getLastName());
        tempPerson.setAge(person.getAge());
        Person.persist(tempPerson);
        return person;
    }
}
