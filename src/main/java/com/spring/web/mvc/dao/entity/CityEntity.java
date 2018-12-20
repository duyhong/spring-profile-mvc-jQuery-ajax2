package com.spring.web.mvc.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities_tbl")
public class CityEntity {
	private int sno;
	private String cityname;
	private Timestamp doe;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	@Column(length=100)
	public String getCityname() {
		return cityname;
	}
	
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	public Timestamp getDoe() {
		return doe;
	}
	
	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}
	
	@Override
	public String toString() {
		return "CityEntity [sno=" + sno + ", cityname=" + cityname + ", doe=" + doe + "]";
	}
	
	
}
