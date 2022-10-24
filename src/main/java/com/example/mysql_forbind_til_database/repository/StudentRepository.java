package com.example.mysql_forbind_til_database.repository;

import com.example.mysql_forbind_til_database.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

  private Connection connection = DatabaseManager.getConnection(); //skal
  //tages ind så man kan hente ting fra databasen og sætte det i listen

  //metode der tager ting fra databasen og ligger det i liste.
  //Student klasse er entity klasse som databaseinstanser laves til
  //gøres på samme måde som med csv fil

  public List<Student> getAllStudents(){
    List<Student> students = new ArrayList<>();

    //PreparedStatement preparedStatement = null; //claus kunne godt instantiere
    //preparedStatement direkte i try catch men her vil den lave den først med null
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student ");
      //laver et objekt med sql statements
      ResultSet resultSet = preparedStatement.executeQuery(); //executer det ovenover
      //while executer det for hver student.

      while (resultSet.next()){ //gøres som csv
        students.add(new Student(
            resultSet.getInt("id"), //her tager den hele kollonen
            //skal have det præcise navn for den kolonne man vil have fra
            //man kan også vælge getInt(0) for kolonne på index 0, her er det id
            //mens kolonne 1 er navn osv.
            //her er index
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getBoolean("gender")
        ));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return students;
  }

  //metode som returnere enkelt student frem for hele tabellen
  public Student getStudent(int id){
    //samme som tidligere men uden arrayliste
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id=? AND name=?");
      //spørgsmålstegnet undgår sql injections
      preparedStatement.setInt(1, id); //parameterindex er første ? i query
      preparedStatement.setString(2, "Claus"); //eksistere kun fordi der er andet ?
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()){ //if frem for while da vi kun skal have én og ikke looope gennem arrayliste
        return new Student( //får fat i en enkelt student
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getBoolean("gender")
        );
      }

    } catch (SQLException e){
      throw new RuntimeException(e);
    }

    return null;
  }

  public void create(Student student) {
    //sql kode til at lægge det i databasen

    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (name, email, gender) VALUES (?,?,?)");
      preparedStatement.setString(1, student.getName()); //parameterindex er første ? i query. ? for de variabler der indsættes
      preparedStatement.setString(2, student.getEmail()); //hver ? variabel indsættes for sig
      preparedStatement.setBoolean(3, student.isGender()); //isGender pga det er boolean
      preparedStatement.executeUpdate();
      //executeUpdate for at lave noget/skabe noget
      //resultSet for at få noget 0



    } catch (SQLException e){
      throw new RuntimeException(e);
    }
  }
//kør dette fra MySqlForbindTilDatabaseApplication for at få det på loalhost
  //kør ikke fra Main klasse

}
