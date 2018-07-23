package com.example.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.entity.Student;
import com.example.respository.StudentRespository;

@Path("/")
public class StudentResource {
	
	@Inject
	private StudentRespository repository;

	@POST
	@Path("student")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(@Context UriInfo uriInfo, Student student) {
		Student s= repository.addStudent(student);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(s.getId())).build()).build();
	}
	
	@GET
	@Path("student/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student findStudentById(@PathParam("id") Long id) {
		return repository.findStudentById(id);
	}
	
	@GET
	@Path("students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> findAllStudents() {
		return repository.findAllStudents();
	}
	
	@PUT
	@Path("student")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudent(Student student) {
		repository.updateStudent(student);	
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("student/{id}")
	public Response removeStudentById(@PathParam("id") Long id) {
		repository.removeStudentById(id);
		return Response.noContent().build();
	}
	
}
