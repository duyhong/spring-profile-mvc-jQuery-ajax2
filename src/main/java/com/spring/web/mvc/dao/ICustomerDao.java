package com.spring.web.mvc.dao;

import java.util.List;

import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

public interface ICustomerDao {

	String validateUser(LoginEntity entity);

	String updateCustomer(CustomerEntity customer);

	void save(CustomerEntity customer);

	CustomerEntity findCustomerByEmail(String email);

	String deleteCustomerByEmail(String email);

	List<CustomerEntity> getCustomers();

	default String deleteCustomerByCid(int cid) {
		return "";
	}

}
