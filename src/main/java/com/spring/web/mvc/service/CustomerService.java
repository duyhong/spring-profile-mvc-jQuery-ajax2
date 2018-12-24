package com.spring.web.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.mvc.advice.Pusher;
import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.dao.CustomerDao;
import com.spring.web.mvc.dao.ICustomerDao;
import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;
import com.spring.web.mvc.model.Customer;
import com.spring.web.mvc.repository.CustomerDaoRepository;

//We have to put @Transactional annotation since we do not dao layer ..............................
@Transactional(propagation=Propagation.REQUIRED)
@Service("CustomerService")
public class CustomerService implements  ICustomerService {

	@Autowired
	private CustomerDaoRepository customerDao;

	
	@Pusher
	@Override
	public String validateUser(Login login){
		/*LoginEntity entity=new LoginEntity();
		BeanUtils.copyProperties(login, entity);
		return customerDao.validateUser(entity);*/
		
		List<LoginEntity> loginEntities=customerDao.authUserByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(loginEntities.size()==0){
			return "fail";
		}else{
			return "success";
		}
	}
	
	@Pusher
	@Override
	public String updateCustomer(Customer customer){
		CustomerEntity entity=new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);
		customerDao.save(entity);
		 return "success";
	}
	
	@Pusher
	@Override
	public void save(Customer customer) {
		CustomerEntity entity=new CustomerEntity();
		BeanUtils.copyProperties(customer, entity);
		customerDao.save(entity);
	}
	
	@Override
	public String deleteCustomerByCid(int cid){
		customerDao.deleteById(cid);
		 return "deleted";
	}
	
	@Pusher
	@Override
	public String deleteCustomerByEmail(String email){
		CustomerEntity customerEntity=customerDao.findByEmail(email);
		customerDao.delete(customerEntity);
		//return customerDao.deleteCustomerByEmail(email);
		 return "deleted";
	}
	
	@Pusher
	@Override
	public Customer findCustomerByEmail(String email){
		CustomerEntity customerEntity=customerDao.findByEmail(email);
		Customer customer=new Customer();
		BeanUtils.copyProperties(customerEntity, customer);
		return customer;
		
	}

	@Pusher(mcode="M910191")
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customersList=new ArrayList<Customer>();
		List<CustomerEntity> list=customerDao.findAll();
		for(CustomerEntity entity:list){
			Customer customer=new Customer();
			BeanUtils.copyProperties(entity, customer);
			customersList.add(customer);
		}
		return customersList;
	}

	@Override
	public void setCustomerDao(CustomerDao customerDao) {
		// TODO Auto-generated method stub
		
	}

}
