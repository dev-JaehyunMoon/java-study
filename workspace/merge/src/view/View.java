package view;

import java.io.IOException;

import dao.NamesDAO;

public class View {
   public static void main(String[] args) throws IOException {
      NamesDAO dao = new NamesDAO();
      dao.createFile(dao.merge());
      
   }
}