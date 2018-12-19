package com.spring.web.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.dao.CustomerDao;
import com.spring.web.mvc.dao.ICustomerDao;
import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;
import com.spring.web.mvc.model.Customer;

//@Transactional(propagation=Propagation.REQUIRED)
@Service("CustomerService")
public class CustomerService implements  ICustomerService {

	@Autowired
	@Qualifier("CustomerDao")
	private ICustomerDao customerDao;

	@Override
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public String validateUser(Login login){
		LoginEntity entity=new LoginEntity();
		BeanUtils.copyProperties(login, entity);
		return customerDao.validateUser(entity);
	}
	
	@Override
	public String updateCustomer(Customer customer){
		CustomerEntity entity=new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);
		return customerDao.updateCustomer(entity);
	}

	@Override
	public void save(Customer customer) {
		CustomerEntity entity=new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);
		customerDao.save(entity);
	}
	
	@Override
	public String deleteCustomerByEmail(String email){
		return customerDao.deleteCustomerByEmail(email);
	}
	
	@Override
	public Customer findCustomerByEmail(String email){
		CustomerEntity customerEntity=customerDao.findCustomerByEmail(email);
		Customer customer=new Customer();
		BeanUtils.copyProperties(customerEntity, customer);
		return customer;
		
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customersList=new ArrayList<Customer>();
		List<CustomerEntity> list=customerDao.getCustomers();
		for(CustomerEntity entity:list){
			Customer customer=new Customer();
			BeanUtils.copyProperties(entity, customer);
			customersList.add(customer);
		}
		return customersList;
	}

}
