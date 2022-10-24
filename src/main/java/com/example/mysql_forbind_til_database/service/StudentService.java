package com.example.mysql_forbind_til_database.service;

import com.example.mysql_forbind_til_database.model.Student;
import com.example.mysql_forbind_til_database.repository.StudentRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class StudentService {

  StudentRepository studentRepository = new StudentRepository();

  public List<Student> getAllStudents(){ //her ville beregninger laves hvis nødvendigt
    return studentRepository.getAllStudents();
  }
  public Student getStudent(int id){
    return studentRepository.getStudent(id);
  }

  public void create(WebRequest request){ //Skal også tage imod webrequest
    //der er både create metode her i service og i repo. Service laver java klasse og repo laver sql insert
    //den skal ikke gøre så meget her men sendes til repository
    //her skal den konvertere det til java klasser så det nemmere lægges i databasen
    //så her laves en student
    //id sendes ikke med pga autogenereret. Overload Student konstruktøren så den også
    //kan lave student uden id sættes ind
    boolean gender = true;
    if(request.getParameter("gender").equalsIgnoreCase("mand")){
      gender = false;
    }

    Student student = new Student(request.getParameter("name"),
        request.getParameter("email"),
        gender);

    studentRepository.create(student);
  }


}
