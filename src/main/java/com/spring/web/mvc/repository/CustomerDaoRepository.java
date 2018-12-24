package com.spring.web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.web.mvc.dao.entity.CustomerEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

@Repository("CustomerDaoRepository")
public interface CustomerDaoRepository extends  JpaRepository<CustomerEntity, Integer> {

	public CustomerEntity findByEmail(String email);
		//custom query to fetch data from Spring Data with JPA
		@Query("SELECT p FROM LoginEntity p WHERE p.username = :username AND p.password= :password")
		public List<LoginEntity> authUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
