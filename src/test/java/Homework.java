import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Homework {

   /*
    1-MedunnaMessageEmailTest
//User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    Statement statement = JdbcUtils.createStatement();
//User sends the query to get the names of "email" column from "cmessage" table
//Assert that there are some "cmessage" email "zeynep05@gmail.com".
//User closes the connection

2- MedunnaPatientTest
//User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    Statement statement = JdbcUtils.createStatement();
//User sends the query to get the names of "patient_id" column from "appointment" table
//Assert that there are some appointment patient_id "405892".
//Assert verify patients have 20295
//User closes the connection
3- MedunnaStaffBirthDay
//User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    Statement statement = JdbcUtils.createStatement();
//User sends the query to get the names of birth_date column from "staff" table
//Assert that there are some staff birth_date "2022-12-03 23:00:00".
//User closes the connection
4-MedunnaColumnNameTest
//User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    Statement statement = JdbcUtils.createStatement();
//User sends the query to get the columns of room table
//Assert verify one of column name is "room_type"
//User closes the connection

    */

    @Test

        //1-MedunnaMessageEmailTest
         //User connects to the database
        //JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        //Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of "email" column from "cmessage" table
        //Assert that there are some "cmessage" email "zeynep05@gmail.com".
        //User closes the connection

    public  void emailTest() {


        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of "email" column from "cmessage" table

        JdbcUtils.executeQuery("cmessage","email");

        List<Object> list = JdbcUtils.listeEkleme("cmessage","email");

        Assert.assertTrue(list.contains("zeynep05@gmail.com"));

        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();


        }

      //  2- MedunnaPatientTest
    //User connects to the database
     //   JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
   // Statement statement = JdbcUtils.createStatement();
    //User sends the query to get the names of "patient_id" column from "appointment" table
    //Assert that there are some appointment patient_id "405892".
    //Assert verify patients have 20295
    //User closes the connection




    @Test
    public void medunnaPatientTest() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        String sql = "Select patient_id from appointment";
        ResultSet rs = statement.executeQuery(sql);
        List<Integer> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add(rs.getInt(1));
        }
        System.out.println(patients.size());//20314
        Assert.assertTrue(patients.contains(405892));
        Assert.assertTrue(patients.size() == 20295);
    }

       // 3- MedunnaStaffBirthDay
        //User connects to the database
        //JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        //Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of birth_date column from "staff" table
        //Assert that there are some staff birth_date "2022-12-03 23:00:00".
        //User closes the connection

    @Test
    public void medunnaStaffBirthDay() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        String sql2 = "Select birth_date from staff where birth_date = '2022-12-03 23:00:00'";
        ResultSet rs = statement.executeQuery(sql2);
        List<String> birthDate = new ArrayList<>();
        while (rs.next()) {
            birthDate.add(rs.getString(1));
        }
        Assert.assertTrue(birthDate.contains("2022-12-03 23:00:00"));
    }

        //4-MedunnaColumnNameTest
        //User connects to the database
        //JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        //Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the columns of room table
        //Assert verify one of column name is "room_type"
        //User closes the connection


    @Test
    public void medunnaColumnNameTest() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();


        //User sends the query to get the columns of room table
        String sql ="select * from room";

      //  boolean sorguYapildiMi = statement.execute(sql);

        //Assert verify one of column name is "room_type"
        String sql2 ="select room_type from room";
        Boolean colonVarMi = statement.execute(sql2);
        Assert.assertTrue(colonVarMi);

        JdbcUtils.closeConnectionAndStatement();






    }
}


