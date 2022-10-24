package com.example.mysql_forbind_til_database.controller;

import com.example.mysql_forbind_til_database.model.Student;
import com.example.mysql_forbind_til_database.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller //Rest fordi vi ikke har html her
public class StudentController {

  StudentService studentService = new StudentService();

  @GetMapping("/")
  public String index(Model model){
    model.addAttribute("studentList", studentService.getAllStudents());
    return "student-list";
  }
  @GetMapping("student")

  public Student getStudent(@RequestParam int id){
    return studentService.getStudent(id);
  }

}
