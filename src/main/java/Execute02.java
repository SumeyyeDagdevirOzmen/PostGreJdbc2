import java.sql.*;

public class Execute02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Goldfish.94");
        Statement st = con.createStatement();
//      boolean sql1 = st.execute("CREATE TABLE students\n" +
//              "(\n" +
//              "\t\n" +
//              "ogrenci_no int,\n" +
//              "isim varchar(20),\n" +
//              "soyisim varchar(25),\n" +
//              "not_ort int,\n" +
//              "kayit_tarih date\n" +
//              "\n" +
//              ");");
//        System.out.println(sql1);
        boolean sql2=st.execute("select * from students");
       System.out.println("sql2 = " + sql2);
        String sql3="select * from students";
        ResultSet rs =st.executeQuery(sql3);
        while(rs.next()){
            System.out.println( rs.getInt(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getInt(4) + "--" + rs.getString(5));
        }
       // JdbcUtils.insertDataIntoTable("Insert into students (ogrenci_no,isim,soyisim,not_ort,kayit_tarih) values('12345690','Ali','Yilmaz',85,'12/08/2020') ");

        JdbcUtils.insertInto("students(ogrenci_no,isim,soyisim,not_ort,kayit_tarih)","'12345690','Ali','Yilmaz',85,'12/08/2020'");




    }






}
