package com.spring.web.mvc.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.CustomerHistoryEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

@Repository("CustomerHibernateDao")
@Transactional(value="txManager")
public class CustomerHibernateDao implements ICustomerDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String validateUser(LoginEntity entity){
		Query query =this.getSession().createQuery("from LoginEntity where username=? and password=?");
		query.setParameter(1,entity.getUsername());
		query.setParameter(2, entity.getPassword());
		try {
			query.getSingleResult();
		}catch(NoResultException exception){
			return "fail";
		}
		return "success";
	}

	@Override
	public String updateCustomer(CustomerEntity customer){

		Query query =this.getSession().createQuery("from CustomerEntity where email=:pemail");
		query.setParameter("pemail",customer.getEmail());
		try {
			//dcustomerEntity is inside the session so if we change the state of this entity 
			//then automatically things would be updated into the database at the
			//end of the transaction
			CustomerEntity dcustomerEntity=(CustomerEntity)query.getSingleResult();
			dcustomerEntity.setCity(customer.getCity());
			dcustomerEntity.setName(customer.getName());
			dcustomerEntity.setMobile(customer.getMobile());
			dcustomerEntity.setPhoto(customer.getPhoto());
			dcustomerEntity.setGender(customer.getGender());
		}catch(NoResultException exception){
			return "fail";
		}
		return "success";
	}

	//REQUIRED ensures this method must be execute inside transaction
	@Override
	public void save(CustomerEntity customer){
		boolean b=TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println("Hibernate tx is enabled = "+b);
		this.getSession().save(customer);
		CustomerHistoryEntity customerHistoryEntity=new CustomerHistoryEntity();
		BeanUtils.copyProperties(customer, customerHistoryEntity);
		this.getSession().save(customerHistoryEntity);
	}

	@Override
	public CustomerEntity findCustomerByEmail(String email){
		CustomerEntity customerEntity=new CustomerEntity();
		Query query =this.getSession().createQuery("from CustomerEntity where email=?");
		query.setParameter(1, email);
		try {
			 customerEntity=(CustomerEntity)query.getSingleResult();
		}catch(NoResultException exception){
			System.out.println(exception.getMessage());
		}
		return customerEntity;
	}

	@Override
	public String deleteCustomerByEmail(String email){
		CustomerEntity customerEntity=new CustomerEntity();
		Query query =this.getSession().createQuery("from CustomerEntity where email=?");
		query.setParameter(1, email);
		try {
			 customerEntity=(CustomerEntity)query.getSingleResult();
			 this.getSession().delete(customerEntity);
		}catch(NoResultException exception){
			return "fail";
		}
		return "deleted";
	}


	@Override
	public List<CustomerEntity> getCustomers(){
		return this.getSession().createQuery("from CustomerEntity").list();
	}
}
