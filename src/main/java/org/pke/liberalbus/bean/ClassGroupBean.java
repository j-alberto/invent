package org.pke.liberalbus.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.pke.liberalbus.domain.ClassGroup;
import org.pke.liberalbus.domain.Student;
import org.springframework.beans.BeanUtils;

public class ClassGroupBean{

	private int id;
	
	@NotBlank
	@Length(min=1, max=10)
	private String name;

	private Date dateAdd;
	
	private Date dateUpdate;
	
	@NotNull
	private byte status;
	
	private List<Student> students;
	
	   @Override
	   public String toString() {
	       return String.format(
	               "GroupBean[id=%d, name='%s, status=%d']",
	               id, name,status);
	   }

	   
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}


	public static ClassGroup toDomain(ClassGroupBean bean) {
		ClassGroup domain = new ClassGroup();
		BeanUtils.copyProperties(bean, domain);
		return domain;
	}

	public static ClassGroupBean parseDomain(ClassGroup domain) {
		ClassGroupBean bean = new ClassGroupBean();
		BeanUtils.copyProperties(domain, bean);
		return bean;
	}

}
