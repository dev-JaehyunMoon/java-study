package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConnecter {
   public static final String BOYPATH = "boyData.txt";
   public static final String GIRLPATH = "girlData.txt";
   public static final String NAMESPATH = "namesData.txt";
   
   public static BufferedWriter getWriter(String path) throws IOException {
      return new BufferedWriter(new FileWriter(path));
   }

   public static BufferedWriter getAppend(String path) throws IOException {
      return new BufferedWriter(new FileWriter(path, true));
   }

   public static BufferedReader getReader(String path) throws IOException {
      return new BufferedReader(new FileReader(path));
   }

}