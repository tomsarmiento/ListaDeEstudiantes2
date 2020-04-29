package springdata2.studentslist.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springdata2.studentslist.models.Contact;
import springdata2.studentslist.models.Dorm;
import springdata2.studentslist.models.Student;
import springdata2.studentslist.services.StudentsListService;

@Controller
public class StudentsListController {
	private final StudentsListService stListServ;
	
	public StudentsListController(StudentsListService stListServ) {
		this.stListServ = stListServ;
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("students", stListServ.allStudents());
		model.addAttribute("dorms", stListServ.allDorms());
		model.addAttribute("id", stListServ.allDorms().get(0).getId());// Iterable<Dorm> ? will it work?
		return "studentslist/index.jsp";
	}
	
	@RequestMapping("/students/new") // I'm afraid this might not work
	public String createStudent(@ModelAttribute Student s) {
		return "studentslist/newstudent.jsp";
	}
	
	@RequestMapping("/students/create") // I'm saving a student through a ModelAttribute in a GET method. Never done this before. Kinda scares me
	public String saveStudent(@Valid @ModelAttribute Student s, BindingResult result) {
		if(result.hasErrors()) {
			return "studentslist/newstudent.jsp";
		}
		else {
			stListServ.createStudent(s);
			return "redirect:/index";
		}	
	}
	
	@RequestMapping("/contact/new")
	public String createContact(@ModelAttribute Contact c, Model model) {
		model.addAttribute("students", stListServ.findByContactNull());
		return "studentslist/newcontact.jsp";
	}
	
	@RequestMapping("/contact/create")
	public String saveContact(@Valid @ModelAttribute Contact c, BindingResult result) {
		if(result.hasErrors()) {
			return "studentslist/newcontact.jsp";
		}
		else {
			stListServ.createContact(c);
			return "redirect:/index";
		}
	}
	
	@RequestMapping("/dorms/new")
	public String createDorm(@ModelAttribute Dorm d) {
		return "studentslist/newdorm.jsp";
	}
	
	@RequestMapping("/dorms/create")
	public String saveDorm(@Valid @ModelAttribute Dorm d, BindingResult rslt) {
		if(rslt.hasErrors()) {
			return "studentlist/newdorm.jsp";
		}
		else {
			stListServ.createDorm(d);
			return "redirect:/index";
		}
	}
	
	@RequestMapping("/dorms/{sDormId}")
	public String dormDetails(@PathVariable String sDormId, Model model) {
		Long lDormId = Long.parseLong(sDormId);
		
		model.addAttribute("dorm", stListServ.findDormById(lDormId));
		model.addAttribute("students", stListServ.findByDormNull());
		return "studentslist/dormdetails.jsp";
	}
	
	@RequestMapping("/dorms/{dormId}/add")
	public String addStudentToDorm(@PathVariable Long dormId, @RequestParam("student") Long studentId) {
		stListServ.addStudentToDorm(studentId, dormId);
		return "redirect:/dorms/"+dormId;
	}
	
	@RequestMapping("/dorms/{dormId}/remove")
	public String removeStudentFromDorm(@PathVariable Long dormId, @RequestParam("student") Long studentId) {
		System.out.println("Dormitorio id:"+dormId+" Estudiante id:"+studentId);
		stListServ.removeStudentFromDorm(studentId, dormId);
		return "redirect:/dorms/"+dormId;
	}
	
}
