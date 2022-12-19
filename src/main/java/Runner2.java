import java.sql.Connection;
import java.sql.Statement;

public class Runner2 {
    public static void main(String[] args) {
        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan
        Connection connection = JdbcUtils.connectToDataBase("localhost", "Techproed", "postgres", "Nihal.2710");

        //3. Adım: Statement oluştur.
        Statement statement = JdbcUtils.createStatement();

        //4. Adım: Query çalıştır.
        //JdbcUtils.execute("CREATE TABLE students (name VARCHAR(20), id INT, address VARCHAR(80))");

        // JdbcUtils.createTable("def","classes VARCHAR(20)","teacher_name VARCHAR(20)","id INT");

      //  JdbcUtils.executeUpdate("update companies set company='APPLE' where company='MICROSOFT'");
      //  JdbcUtils.executeQuery("companies", "company_id", "company", "number_of_employees");
        //5. Adım: Bağlantı ve Statement'ı kapat.
        JdbcUtils.closeConnectionAndStatement();

    }
}