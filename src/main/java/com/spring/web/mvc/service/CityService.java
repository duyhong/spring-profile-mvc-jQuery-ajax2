package com.spring.web.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.web.mvc.dao.ICityDao;

@Service("CityService")
public class CityService implements ICityService {

	@Autowired //byTpe , @Qualifier , byName
	@Qualifier("CityHibernateDao")
	private ICityDao cityDao;
	
	@Override
	public List<String> findCities(){
		return cityDao.findCities();
	}

}
