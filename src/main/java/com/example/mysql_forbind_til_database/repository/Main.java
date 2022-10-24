package com.example.mysql_forbind_til_database.repository;

import com.example.mysql_forbind_til_database.model.Student;

public class Main {
  public static void main(String[] args) {
    StudentRepository studentRepository = new StudentRepository();
    System.out.println(studentRepository.getAllStudents());
    System.out.println(studentRepository.getStudent(33));
    //laves bare for at teste programmmet
  }
}
