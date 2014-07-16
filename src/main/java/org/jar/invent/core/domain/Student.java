package org.jar.invent.core.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="STUDENT")
public class Student {


	protected Student() {}
	
	public Student(int id, String code, String name, String lastName) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.lastName = lastName;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="student_id")
    private int id;
    @Column(name="student_name")
	private String name;
    @Column(name="student_lname")
    private String lastName;
    @Column(name="student_code")
    private String code;
    @ManyToMany(mappedBy="students")
    private List<ClassGroup> groups;


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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ClassGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ClassGroup> groups) {
		this.groups = groups;
	}

    @Override
   public String toString() {
       return String.format(
               "Student[id=%d, code='%s' name='%s', lname='%s']",
               id, code, name, lastName);
   }

}
