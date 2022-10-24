package com.example.mysql_forbind_til_database.controller;

import com.example.mysql_forbind_til_database.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller //Controller returnere tekst streng
public class HomeController {

  private StudentService studentService = new StudentService();

  @GetMapping("/create") //opret studerende i databasen
  public String index(){
    return "index";
  }
  //RPG betoder post redirect get - design metode, når man poster
  @PostMapping("/create") //endpointet skal være det samme. når man skriver create i
  //urlen i browseren så er det altid get
  public String create(WebRequest request){ //data i html ligger i request parametren
    studentService.create(request); //sender html formen i request tl service

    System.out.println(request.getParameter("name"));//det her er bare for at teste om vi får noget ind
    System.out.println(request.getParameter("email"));
    System.out.println(request.getParameter("gender"));
    //return "index"; her returneres formen igen så man er i tvivl om man overhoved gemte ting man har tastet ting ind
    return "redirect:/"; //her returneres en anden side efter, brug "redirect:html_side"
    //nu returneres index fordi / og det er bare hvad der ligger i databasen med studerende
    //det er i den gamle studentController som har / endpointet og poster
    // all students

    //når noget tastes ind på localhost kan man se det i intellij konsollen pga sout
    //i stdet skal det sendes til serives mappen og laves tio java student klasse
    // og så til repo og lægges i database

  }

}
