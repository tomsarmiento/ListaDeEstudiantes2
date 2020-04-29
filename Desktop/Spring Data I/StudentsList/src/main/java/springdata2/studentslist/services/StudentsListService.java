package springdata2.studentslist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springdata2.studentslist.models.Contact;
import springdata2.studentslist.models.Dorm;
import springdata2.studentslist.models.Student;
import springdata2.studentslist.repositories.ContactRepository;
import springdata2.studentslist.repositories.DormRepository;
import springdata2.studentslist.repositories.StudentRepository;

@Service
public class StudentsListService {
	private final StudentRepository stRepo;
	private final ContactRepository ctRepo;
	private final DormRepository dormRepo;
	
	public StudentsListService(StudentRepository stRepo, ContactRepository ctRepo, DormRepository dormRepo) {
		this.stRepo = stRepo;
		this.ctRepo = ctRepo;
		this.dormRepo = dormRepo;
	}
	
	public List<Student> allStudents(){
		return stRepo.findAll();
	}
	
	public void createStudent(Student s) {
		stRepo.save(s);
	}
	
	public void createContact(Contact c) {
		ctRepo.save(c);
	}
	
	public void createDorm(Dorm d) {
		dormRepo.save(d);
	}
	
	public List<Dorm> allDorms(){
		return (List<Dorm>) dormRepo.findAll();
	}
	public Dorm findDormById(Long id) {
		Optional<Dorm> optDorm = dormRepo.findById(id);
		if(optDorm.isPresent()) {
			return optDorm.get();
		}
		else {
			return null;
		}
	}
	
	public void addStudentToDorm(Long studentId, Long dormId) {
		Student student = stRepo.findById(studentId).get();
		Dorm d = dormRepo.findByid(dormId);
		student.setDorm(d);
		List<Student> students = d.getStudents();
		students.add(student);
		d.setStudents(students);
		stRepo.save(student);
		dormRepo.save(d);
	}

	public void removeStudentFromDorm(Long studentId, Long dormId) {
		Student st = stRepo.findById(studentId).get();;
		Dorm d = dormRepo.findByid(dormId);
		List<Student> students = d.getStudents();
		students.remove(st);
		d.setStudents(students);
		st.setDorm(null);
		stRepo.save(st);
		dormRepo.save(d);
		
	}
	
	public List<Student> findByDormNull(){
		Optional<List<Student>> optListSt = stRepo.findByDormNull();
		if(optListSt.isPresent()) {
			return optListSt.get();
		}
		else {
			return null;
		}
	}
	
	public List<Student> findByContactNull(){
		Optional<List<Student>> optListSt = stRepo.findByContactNull();
		if(optListSt.isPresent()) {
			return optListSt.get();
		}
		else {
			return null;
		}
	}
}
