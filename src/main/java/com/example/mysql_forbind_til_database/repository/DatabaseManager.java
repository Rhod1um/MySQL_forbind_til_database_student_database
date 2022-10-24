package com.example.mysql_forbind_til_database.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
  //database kontakt er altid i repository
  //husk vælg SQL - MySQL driver ved opstart

  //skal være static så man kun laver én connection til databasen
  private static String hostname;
  private static String username;
  private static String password;
  private static Connection connection;
  //laver connection instans som connecter til databasen

  //gettere og settere er også static. Gør at alle klasser kan connecte
  //databasen ved at genne denne
  public static Connection getConnection() {

    hostname = "jdbc:mysql://mindatabase.mysql.database.azure.com/kea"; //en url,
    //jdbc - java database connector?
    //mysql-protokol frem for http. Husk at lave /databasenavn til sidst
    username = "mauf8";
    password = "Unika007!";
    //try-catch skal med når man forbinder til database, ligesom med filer
    try {
      connection = DriverManager.getConnection(hostname, username, password);
      System.out.println("Forbindelse til database success"); //skriv på dansk så man ved det er en selv, der har skrevet det
    } catch (SQLException e) {
      System.out.println("Kan ikke forbinde til database");
      throw new RuntimeException(e);
    }
    return connection;
  }
}
