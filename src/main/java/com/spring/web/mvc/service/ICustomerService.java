package com.spring.web.mvc.service;

import java.util.List;

import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.dao.CustomerDao;
import com.spring.web.mvc.model.Customer;

public interface ICustomerService {

	String validateUser(Login login);

	String updateCustomer(Customer customer);

	void save(Customer customer);

	String deleteCustomerByEmail(String email);

	Customer findCustomerByEmail(String email);

	List<Customer> getCustomers();

	void setCustomerDao(CustomerDao customerDao);

}
