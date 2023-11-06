package com.test;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    @Transactional
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        System.out.println("In add Person method");
//        Person tempPerson = new Person();
//        tempPerson.setFirstName(person.getFirstName());
//        tempPerson.setLastName(person.getLastName());
//        tempPerson.setAge(person.getAge());
        Person newPerson = new Person(person.getFirstName(), person.getLastName(), person.getAge());
        Person.persist(newPerson);
        return person;
    }

    @POST
    @Path("/addperson2")
    @Transactional
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Person addPerson2(Person person) {
        System.out.println("In add Person method 2");
        Person.persist(person);
        return person;
    }


}
