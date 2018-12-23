package com.spring.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.mvc.controller.model.ApplicationResponse;
import com.spring.web.mvc.model.Customer;
import com.spring.web.mvc.service.CustomerService;

@RestController
public class RestProfileController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * 
	 * @return
	 * @RequestBody - is reading json data from body of incoming request and converting into Java Object
	 * using jackson mapper framework!
	 */

	@PostMapping("/customers")
	public ApplicationResponse uploadCustomer(@RequestBody Customer customer){
		System.out.println(customer);
		ApplicationResponse applicationResponse=new  ApplicationResponse();
		applicationResponse.setMessage("Hey! your data is received here.............");
		applicationResponse.setStatus("succes");
		applicationResponse.setPath("coming soon!");
		return applicationResponse;
	}

	/**
	 * Below method will return json response 
	 * of all the customers available into our database
	 * @param model
	 * @return
	 */
	@GetMapping("/customers")//alias
	public List<Customer> showData(){
		List<Customer> customers=customerService.getCustomers();
		return customers;
	}

}
