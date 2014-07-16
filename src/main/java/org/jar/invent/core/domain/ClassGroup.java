package org.jar.invent.core.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="CLASS_GROUP")
public class ClassGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_group_id")
	private int id;
	
	@Column(name="class_group_name")
//	@NotBlank
//	@Length(min=1, max=10)
	private String name;

	@Column(name="date_add")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdd;
	
	@Column(name="date_upd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;
	
	@Column(name="user_add")
//	@Length(min=1, max=10)
	private String userAdd;
	
	@Column(name="user_upd")
//	@Length(min=1, max=10)
	private String userUpdate;
	
	@Column
//	@NotNull
	private byte status;
	
	@ManyToMany
	@JoinTable(name="GROUP_STUDENT",
		joinColumns={@JoinColumn(name="class_group_fk",referencedColumnName="class_group_id")},
		inverseJoinColumns={@JoinColumn(name="student_fk",referencedColumnName="student_id")})
	private List<Student> students;
	
	   @Override
	   public String toString() {
	       return String.format(
	               "Group[id=%d, name='%s, status=%d', dateAdd=%s, dateUpd=%s]",
	               id, name,status,dateAdd, dateUpdate);
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
	public String getUserAdd() {
		return userAdd;
	}
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}

}
