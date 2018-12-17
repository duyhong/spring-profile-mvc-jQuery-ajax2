package com.spring.web.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;


@Repository("CustomerDao")
public class CustomerDao implements ICustomerDao {
	
	@Autowired
	@Qualifier("pjdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String validateUser(LoginEntity entity){
		System.out.println("Before saving\n");
		String sql="select  username from logins_tbl where username=? and password=?";
		try {
			jdbcTemplate.queryForObject(sql,new Object[]{entity.getUsername(),entity.getPassword()},new BeanPropertyRowMapper(LoginEntity.class));	
		}catch(Exception ex){
			return "fail";
		}
		return "success";
	}
	
	@Override
	public String updateCustomer(CustomerEntity customer){
		System.out.println("Before Updating\n");
		String sql="update profile set name=?,gender=?,mobile=?,city=?,photo=? where email=?";
		Object data[] = new Object[]{customer.getName(),customer.getGender(),customer.getMobile(),customer.getCity(),customer.getPhoto(),customer.getEmail()};
		jdbcTemplate.update(sql,data);	
		return "success";
	}

	//REQUIRED ensures this method must be execute inside transaction
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(CustomerEntity customer){
		boolean b=TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println("Transaction status = "+b);
		System.out.println("Before saving\n");
		String sql1="insert into profile(name,email,gender,mobile,photo,city) values(?,?,?,?,?,?)";
		Object data[] = new Object[]{customer.getName(),customer.getEmail(),customer.getGender(),customer.getMobile(),customer.getPhoto(),customer.getCity()};
		System.out.println(jdbcTemplate);
		//Query1
		jdbcTemplate.update(sql1,data);
		
		String sql2="insert into profile_history_tbl(name,email,gender,mobile,photo,city) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql2,data);
		System.out.println("Completed saving");
	}
	
	@Override
	public CustomerEntity findCustomerByEmail(String email){
		String sql="select sno,name,email,gender,mobile,photo,city from profile where email=?";
		CustomerEntity customerEntity=(CustomerEntity)jdbcTemplate.queryForObject(sql,new Object[]{email},new BeanPropertyRowMapper(CustomerEntity.class));	
		return customerEntity;
	}
	
	@Override
	public String deleteCustomerByEmail(String email){
		String sql="delete from  profile where email=?";
		int row=jdbcTemplate.update(sql, new Object[]{email});	
		return row>0?"deleted":"fail";
	}
	
	@Override
	public List<CustomerEntity> getCustomers(){
		List<CustomerEntity> customerList = new ArrayList<CustomerEntity>();
		String sql="select sno,name,email,gender,mobile,photo,city from profile";
		customerList=jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerEntity.class));	
		return customerList;
	}

}
