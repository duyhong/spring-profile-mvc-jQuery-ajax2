package com.spring.web.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/customers")//alias
	public List<Customer> showData(){
		List<Customer> customers=new ArrayList<Customer>();
		customers=customerService.getCustomers();
		return customers;
	}

	/**
	 * Below method will return json response 
	 * of all the customers available into our database
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/customers",params ={"search"})//alias
	public List<Customer> searchCustomers(@RequestParam(value="search",required=false) String search){
		List<Customer> customers=new ArrayList<Customer>();
			customers=customerService.getCustomers();
		return customers;
	}

	/**
	 * Below method will return json response 
	 * of all the customers available into our database
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/customers",params ={"sort"})//alias
	public List<Customer> sortCustomers(@RequestParam(value="sort",required=false) String sort){
		List<Customer> customers=new ArrayList<Customer>();
			customers=customerService.getCustomers();
		return customers;
	}

	/**
	 * Below method will return json response 
	 * of all the customers available into our database
	 * @param model
	 * @return
	 */
	//	companies?page=23
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/customers",params ={"page"})//alias
	public List<Customer> findCustomersByPage(@RequestParam(value="page",required=false) int page){
		List<Customer> customers=new ArrayList<Customer>();
			customers=customerService.getCustomers();
			return customers;
	}


	@PutMapping("/customers")
	public ApplicationResponse iuploadCustomer(@RequestBody Customer customer){
		System.out.println(customer);
		//write code to update
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
	//below is an example of sending data as part of URI
	//http://localhost:8080/customers/90292
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/customers/{cid}")//alias
	public ApplicationResponse deleteCustomerByCid(@PathVariable("cid") int pcid){
		customerService.deleteCustomerByCid(pcid);
		ApplicationResponse applicationResponse=new  ApplicationResponse();
		applicationResponse.setMessage("Hey! your data is delete here.............");
		applicationResponse.setStatus("succes");
		applicationResponse.setPath("coming soon!");
		return applicationResponse;
	}
}
