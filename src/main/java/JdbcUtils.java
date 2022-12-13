import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    //1.Adim: Drive'a kaydol
    //2.Adim : Database'e baglan
   private static Connection connection;
    private static Statement statement;

    public static Connection connectToDataBase(String hostName,String dbName,String userName,String password){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Success");
        }else{
            System.out.println("Connection Fail!");
        }

        return connection;


    }

    //3.Adim:Statement olustur.
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //4.Adim:Query calistir.

    public static boolean execute(String sql){

        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //5.Adim: Baglanti ve Statement'i kapat.

    public static void closeConnectionAndStatement(){

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed!");
            }else{
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
