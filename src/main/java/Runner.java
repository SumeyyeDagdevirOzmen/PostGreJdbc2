import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main (String[]args){
            //1. Adım: Driver'a kaydol
            //2. Adım: Datbase'e bağlan
            Connection connection = JdbcUtils.connectToDataBase("localhost", "techproed", "postgres", "Goldfish.94");

            //3. Adım: Statement oluştur.
            Statement statement = JdbcUtils.createStatement();


            //4. Adım: Query çalıştır.
            //JdbcUtils.execute("CREATE TABLE students (name VARCHAR(20), id INT, address VARCHAR(80))");

            // JdbcUtils.createTable("def","classes VARCHAR(20)","teacher_name VARCHAR(20)","id INT");

            //JdbcUtils.executeUpdate("companies", "company", "'HUWAE'", "company='APPLE'");

       JdbcUtils.executeUpdate("update companies set company='GOOGLE' where company='HUWAE'");
        System.out.println("--------------------");
            JdbcUtils.insertInto("companies (number_of_employees)", "'10000'");
        System.out.println("+++++++++++++++++++++++++++++++");


          JdbcUtils.executeQuery("companies", "company_id", "company", "number_of_employees");
//
//        String query ="select * from companies";
//        ResultSet resultSet =JdbcUtils.executeQuery(query);
//        while(true){
//            try {
//                if (!resultSet.next()) break;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        while(true){
//            try {
//                if (!resultSet.next()) break;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }


           // System.out.println(JdbcUtils.listeEkleme("companies", "company"));
            //5. Adım: Bağlantı ve Statement'ı kapat.
            JdbcUtils.closeConnectionAndStatement();

        }
    }