package com.example.cucumber;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.test.api.ArquillianResource;

import com.example.entity.Student;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StudentCucumberSteps {
	private Response response;
	private Student student;
	private int studentId;

	@ArquillianResource
	URL baseURL;
	
	@Given("The student details are as below:")
	public void setup(List<Student> student) {
		this.student = student.get(0);
	}
	
	@When("^I make a GET call to \"(.*?)\" endpoint$")
	public final void I_make_a_GET_call_to_endpoint(final String endpointUrl) throws Throwable {
		response = ClientBuilder.newClient().target(baseURL+endpointUrl+studentId).request().get();
	}
	
	@When("^I make a GET call to find all students to  \"([^\"]*)\" endpoint$")
	public final void I_make_a_GET_call_to_find_all(final String endpointUrl) throws Throwable {
		response = ClientBuilder.newClient().target(baseURL+endpointUrl).request().get();
	}
	
	@When("^I make a POST call to \"(.*?)\" endpoint$")
	public final void I_make_a_POST_call_to_endpoint(final String endpointUrl) throws Throwable {
		response = ClientBuilder.newClient().target(baseURL+endpointUrl)
								.request()
								.post(Entity.entity(student, MediaType.APPLICATION_JSON));
	}
	
	@When("^I make a PUT call to \"(.*?)\" endpoint$")
	public final void I_make_a_PUT_call_to_endpoint(final String endpointUrl) throws Throwable {
		response = ClientBuilder.newClient().target(baseURL+endpointUrl)
								.request()
								.put(Entity.entity(student, MediaType.APPLICATION_JSON));
	}
	
	@When("^I make a DELETE call to \"(.*?)\" endpoint$")
	public final void I_make_a_DELETE_call_to_endpoint(final String endpointUrl) throws Throwable {
		response = ClientBuilder.newClient().target(baseURL+endpointUrl+studentId).request().delete();
	}
	
	@Given("^The student id is (\\d+):$")
	public void the_student_id_is(int id) throws Throwable {
		this.studentId = id;
	}
	
	@Then("^response status code should be (\\d+)$")
	public final void response_status_code_should_be(final int statusCode) throws Throwable {
		assertThat(this.response.getStatus(), equalTo(statusCode));
	}

	@Then("^response content type should be \"(.*?)\"$")
	public final void response_content_type_should_be(final String contentType) throws Throwable {
		assertThat(this.response.getMediaType().toString(), equalTo(contentType));
	}
	
	@Then("^response should be json:$")
	public final void response_should_be_json(final String jsonResponseString) throws Throwable {
		assertJsonEquals(jsonResponseString, response.readEntity(String.class));
	}
}

