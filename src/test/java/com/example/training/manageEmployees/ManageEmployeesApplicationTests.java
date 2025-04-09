package com.example.training.manageEmployees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.training.manageEmployees.bean.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManageEmployeesApplicationTests {

	@LocalServerPort
    Integer serverPort; //Server port will be injected here during Testcase execution.

	@Test
	void contextLoads() {
	}
	
	@Test
	void testQueryEmployee() {
		RestTemplate template = new RestTemplate();
		//Invoke http://localhost:8080/employees/100 using GET
		ResponseEntity<Employee> response = template.getForEntity("http://localhost:"+serverPort+"/employees/100", Employee.class);
		//Checking if the response object name is 'James Cooper' ==> If yes, then case is passed.
		assertEquals(response.getBody().getEmpName(), "James Cooper");		
	}
	@Test
	void testQueryEmployeeWithNonExists() {
	    RestTemplate template = new RestTemplate();
	    String url = "http://localhost:" + serverPort + "/employees/900"; 

	    HttpClientErrorException.NotFound exception = assertThrows(
	        HttpClientErrorException.NotFound.class,
	        () -> template.getForEntity(url, String.class)
	    );

	    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
	    assertTrue(exception.getResponseBodyAsString().contains("Employee with given id not found"));
	}


}
