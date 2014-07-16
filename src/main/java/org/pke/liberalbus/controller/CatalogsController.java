package org.pke.liberalbus.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.pke.liberalbus.domain.ClassGroup;
import org.pke.liberalbus.domain.Student;
import org.pke.liberalbus.domain.dao.ClassGroupRepository;
import org.pke.liberalbus.domain.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/groups")
public class CatalogsController{

	@Autowired
	private StudentRepository studentDAO;
	@Autowired
	private ClassGroupRepository classGroupDAO;

	/**
	 * <p>Defines mappings of the form 'template :: fragment' for catalog requests. 
	 * Each CAT_FRAG contains the mapping in its <b>value</b> attribute</p>
	 * <p>Its String representation can be used as identifier for model attributes in Model's response</p>
	 * 
	 * @author zero
	 *
	 */
	
	public static final String TEMPLATE_GROUP_PAGE = "general/groups";
	public static final String TEMPLATE_GROUP_ADD = "frags/studentFragments :: classGroupAdd";
	public static final String TEMPLATE_GROUP_LIST = "general/groups :: classGroupList";

//	public static final String URL_GROUP_PAGE = "/groups";
//	public static final String URL_GROUP_ADD = "/groups/add";
//	public static final String URL_GROUP_LIST = "/groups/list";
	
	public static final String PARAM_GROUP_LIST = "classGroups";
	public static final String PARAM_GROUP = "classGroup";
		
	@RequestMapping(method=RequestMethod.GET)
	public String showGroups(Model model){
		List<ClassGroup> groups = (List<ClassGroup>)classGroupDAO.findAll();
		model.addAttribute(PARAM_GROUP_LIST, groups);
		
		return TEMPLATE_GROUP_PAGE;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String addGroup(@Valid final ClassGroup classGroup, final BindingResult bresult, final ModelMap model){
		Logger.getInstance(this.getClass()).info("group to save: "+classGroup);
		
		if(bresult.hasErrors()){
			return TEMPLATE_GROUP_ADD;
		}
		
		classGroupDAO.save(classGroup);
		model.clear();

		return "redirect:/groups";
	}

//	
//	@RequestMapping("/catalogs/group/list/{name}")
//	public String loadGroupList(@PathVariable(value="name") String name
//									,Model model){
//		
//		List<ClassGroup> groups = classGroupDAO.findByName(name);
//
//		model.addAttribute(URL_FRAGS.GROUP_LIST.toString(), groups);
//		
//		return URL_FRAGS.GROUP_LIST.value;
//	}

	
//	@RequestMapping("/catalogs/student/list")
//	public String loadStudentList(Model model){
//		List<Student> students = (List<Student>)studentDAO.findAll();
//		model.addAttribute(URL_FRAGS.STUDENT_LIST.toString(),students);
//		
//		return URL_FRAGS.STUDENT_LIST.value;
//	}
//	
//	@RequestMapping("/catalogs/student/list/{lname}")
//	public String loadStudentList(@PathVariable(value="lname") String lastName
//									,Model model){
//		
//		List<Student> students = studentDAO.findByLastName(lastName);
//		model.addAttribute(URL_FRAGS.STUDENT_LIST.toString(), students);
//
//		return URL_FRAGS.STUDENT_LIST.value;
//	}
//	
	@ModelAttribute(PARAM_GROUP)
	public ClassGroup classGroup(){
		return new ClassGroup();
	}
	
	
	void setStudentRepository(StudentRepository studentDAO) {
		this.studentDAO = studentDAO;
	}
	void setClassGroupRepository(ClassGroupRepository classGroupDAO) {
		this.classGroupDAO = classGroupDAO;
	}
	
}
