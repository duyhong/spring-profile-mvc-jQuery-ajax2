package com.spring.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.mvc.controller.model.ApplicationResponse;
import com.spring.web.mvc.model.Customer;
import com.spring.web.mvc.service.CustomerService;
import com.spring.web.mvc.service.ICityService;

@Controller
public class ProfilesController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ICityService cityService;
	
	
	@GetMapping("/editCustomer")
	public String editCustomer(@RequestParam("email") String email,Model model){
		//String email=request.getParameter("email");
		Customer customer=customerService.findCustomerByEmail(email);
		//Here we are mapping customer object with customerForm
		model.addAttribute("customerForm", customer);
		return "editProfile"; //welcome.jsp
	}
	
	@GetMapping("/jdeleteCustomer")
	@ResponseBody
	public ApplicationResponse jdeleteCustomer(@RequestParam("email") String email,Model model){
		//String email=request.getParameter("email");
		String status=customerService.deleteCustomerByEmail(email);
		//Show remaining data now
		///List<Customer> customers=customerService.getCustomers();
		//model.addAttribute("customers",customers);
		//return "redirect:/show-data"; //welcome.jsp
		ApplicationResponse applicationResponse=new ApplicationResponse();
		applicationResponse.setMessage("Hey record is deleted with email = "+email);
		applicationResponse.setStatus(status);
		return applicationResponse;
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("email") String email,Model model){
		//String email=request.getParameter("email");
		customerService.deleteCustomerByEmail(email);
		//Show remaining data now
		///List<Customer> customers=customerService.getCustomers();
		//model.addAttribute("customers",customers);
		return "redirect:/show-data"; //welcome.jsp
	}
	
	@GetMapping({"/show-data","/"})//alias
	public String showData(Model model){
		List<Customer> customers=customerService.getCustomers();
		model.addAttribute("customers",customers);
		return "showCustomers"; //welcome.jsp
	}
	
	@GetMapping("/add-profile")
	public String showProfilePage(){
		return "addProfile"; // /WEB-INF/jsps/addProfile.jsp
	}
	
	@PostMapping("/edit-profile")
	@ResponseBody public ApplicationResponse editProfile(@ModelAttribute Customer customer){
		System.out.println("____updating customer data");
		System.out.println("controller - customer: " + customer);
		//We will code to update customer into the database
		String status=customerService.updateCustomer(customer);
		ApplicationResponse applicationResponse=new ApplicationResponse();
		applicationResponse.setMessage("Hey record is updated successfully with email = "+customer.getEmail());
		applicationResponse.setStatus(status);
		return applicationResponse;
	}
	
	@PostMapping("/add-profile")
	public String addProfile(@ModelAttribute Customer customer,Model model){
		/*String name=request.getParameter("name");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String mobile=request.getParameter("mobile");
		String city=request.getParameter("city");
		String photo=request.getParameter("photo");*/
		//Profile profile=new Profile(name, email, gender, city, mobile,photo);
		model.addAttribute("profile",customer);
		return "reviewProfile"; //welcome.jsp
	}
	
	
	@PostMapping("/save-review-profile")
	public String saveReviewProfile(@ModelAttribute Customer customer,Model model){
		//String name=request.getParameter("name");
		//String email=request.getParameter("email");
		//String gender=request.getParameter("gender");
		///String mobile=request.getParameter("mobile");
		//String city=request.getParameter("city");
		//String photo=request.getParameter("photo");
		//Profile profile=new Profile(name, email, gender, city, mobile,photo);
		//Customer customer = new Customer(name, email, gender, city, mobile,photo);
		///Here we have to write code to save data into database
		customerService.save(customer);
		//request.setAttribute("profile",profile);
		List<Customer> customers=customerService.getCustomers();
		model.addAttribute("customers",customers);
		return "showCustomers"; //welcome.jsp
	}
	
	@ModelAttribute("cities") // all the return cities are added inside request scope with this key = "cities"
	public List<String> loadCities(){
		List<String> pcities=cityService.findCities();
		return pcities;
	}
}
