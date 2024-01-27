package com.test;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

// TODO: Add request response to the api methods
// TODO: Refactore and do service methods instead
// TODO: Add tests to the api methods in corresponding test-file

@Path("/api/v1")
public class PersonResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    // Add get list of all persons
    @GET
    @Path("/allpersons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return Person.listAll(); // return list directly, instead of just findAll()

        // If the method returns ArrayList, can use this code:
//        List<Person> personList = Person.listAll();
//        return new ArrayList<>(personList);
    }

    // Add getPersonById; id as pathvar (pathparam)
    @GET
    @Path("/personbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("id") long id) {
        return Person.findById(id);
    }

    // Add getPersonById; id as req-body field
    // Response shown in Postman or terminal
    // (Run tool in intelliJ is not available in quarkus dev mode)
    @GET
    @Path("/personresponsebyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonResponseById(@PathParam("id") long id) {
        Person person = Person.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    @Path("/addperson")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        System.out.println("In add Person method");
//        Person tempPerson = new Person();
//        tempPerson.setFirstName(person.getFirstName());
//        tempPerson.setLastName(person.getLastName());
//        tempPerson.setAge(person.getAge());
        Person newPerson = new Person(person.getFirstName(), person.getLastName(), person.getAge());
        Person.persist(newPerson);
        return newPerson;
    }

    // Use primarily this shortened post method
    @POST
    @Path("/addperson2")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person addPerson2(Person person) {
        System.out.println("In add Person method 2");
        Person.persist(person);
        return person;
    }

    @PUT
    @Path("/updateperson")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person updatePerson(Person person) {
        // Find personToUpdate by id form req body
        // (or add long id as param besides req body)
        System.out.println(person.getId());
        Person personToUpdate = Person.findById(person.getId());
//        if(personToUpdate == null) {
//            throw new NotFoundException();
//        }

        System.out.println(personToUpdate.getLastName());

        // If personToUpdate exists by id
        // map new properties req body person to the found personToUpdate
        // (ignore by now to handle null or excepted property values)
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setAge(person.getAge());


        return personToUpdate;
    }

    @DELETE
    @Path("/deleteperson")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(Person person){
        Person personToDelete = Person.findById(person.getId());
        Person.deleteById(personToDelete.getId());
    }


}
