import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Fail");
        }
        return connection;
    }
    //3.adim statement olustur
    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    //4. Adım: Query çalıştır.
    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //ExecuteQuery methodu
//    public static ResultSet executeQuery(String query) {
//
//        try {
//            resultSet = statement.executeQuery(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return resultSet;
//    }

    //ExecuteUpdate methodu
//    public static int executeUpdate(String query) {
//        int guncellenenSatirSayisi;
//
//        try {
//            guncellenenSatirSayisi = statement.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return guncellenenSatirSayisi;
//    }
//



//    //(ODEV!!!)      executeQuery--Tabloyu gormek icin kullaniyoruz
    public static void  executeQuery (String tableName, String... columnName){
        StringBuilder columns= new StringBuilder("");
        for(String w:columnName){
            columns.append(w).append(",");
        }
        columns.deleteCharAt(columns.length()-1);
        Statement statement=JdbcUtils.createStatement();
        try {
            String sql2="select   "+columns+" from "+tableName;
            ResultSet resultset1=statement.executeQuery(sql2);
            while(resultset1.next()){
                for (String j:columnName){
                    System.out.print(resultset1.getObject(j)+" ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void  executeQuery2(String tableName,String where, String... columnName){
        StringBuilder columns= new StringBuilder("");
        for(String w:columnName){
            columns.append(w).append(",");
        }
        columns.deleteCharAt(columns.length()-1);
        Statement statement=JdbcUtils.createStatement();
        try {
            String sql2="select   "+columns+" from "+tableName +"where"+where;
            ResultSet resultset1=statement.executeQuery(sql2);
            while(resultset1.next()){
                for (String j:columnName){
                    System.out.print(resultset1.getObject(j)+" ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







    //(ODEV!!!)      executeUpdate-Tabloda guncelleme yapma methodu
//    public static void executeUpdate(String tableName, String setWhat, String newWalue, String where){
//        Statement statement=JdbcUtils.createStatement();
//        try {
//            String sql1="UPDATE "+tableName+"\n" +
//                    "SET "+setWhat+"="+newWalue+"\n" +
//                    "WHERE "+where;
//            int updateEdilenSatirSayisi = statement.executeUpdate(sql1);
//            System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);
//
//            System.out.println("Table updated succesfully! ");
//            //ResultSet resultSet1 = statement.executeQuery("select * from "+tableName );
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //ExecuteUpdate methodu
    public static int executeUpdate(String query) {
        int guncellenenSatirSayisi;

        try {
            guncellenenSatirSayisi = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guncellenenSatirSayisi;
    }




    //(ODEV!!!)        tabloya eleman ekleme methodu
    public static void  insertInto (String tableName, String... values){
        StringBuilder degerler= new StringBuilder("");
        for(String w:values){
            degerler.append(w).append(",");
        }
        degerler.deleteCharAt(degerler.length()-1);
        Statement statement=JdbcUtils.createStatement();
        try {
            String sql2="INSERT INTO "+tableName+" VALUES ("+ degerler+")";
            int eklenenSatirSayisi = statement.executeUpdate(sql2);
            System.out.println("eklenenSatirSayisi = " + eklenenSatirSayisi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    //Table'a data girme methodu
//    public static void insertDataIntoTable(String tableName, String... columnName_Value) {
//
//
//        StringBuilder columnNames = new StringBuilder("");
//        StringBuilder values = new StringBuilder("");
////insert into students(name,age,date) values ("'ali',23,'12/10/2022,'"); ali,23,12/10/2022,
//        for (String w : columnName_Value) {
//            columnNames.append(w.split(" ")[0]).append(",");//Bir String değeri ikiye bölüp birinciyi sütun adı, ikinciyi sütun değeri olarak alıyorum.
//            values.append(w.split(" ")[1]).append(",");
//        }
//
//       columnNames.deleteCharAt(columnNames.length()-1);
//        //columnNames.deleteCharAt(columnNames.lastIndexOf(","));//En son virgülü siliyor.
//       // values.deleteCharAt(values.lastIndexOf(","));
//        values.deleteCharAt(values.length()-1);
//        //"INSERT INTO      members     ( id, name, address ) VALUES(123, 'john', 'new york')"
//        String query = "INSERT INTO " + tableName + "(" + columnNames + ") VALUES(" + values + ")";
//
//        execute(query);//execute methodu üstte oluşturuldu, query'yi çalıştırıyor.
//        System.out.println("Data " + tableName + " tablosuna girildi.");
//
//    }








    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType ){
        StringBuilder columnName_dataValue = new StringBuilder("");
        for(String w : columnName_dataType){
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);
        try {
            statement.execute( "CREATE TABLE "+tableName+"("+columnName_dataValue+")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //(ODEV!!!)      Tablodaki herhangi bir sutunu Liste ekleme methodu
    public static List<Object> listeEkleme(String tableName, String fieldName ){
        Statement st =JdbcUtils.createStatement();
        String query = "Select "+fieldName+ " from "+ tableName;
        List<Object> fields=new ArrayList<>();
        try {
            ResultSet resultSet= st.executeQuery(query);
            while (resultSet.next()){
                fields.add(resultSet.getObject(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return fields;
    }
    //5. Adım: Bağlantı ve Statement'ı kapat.
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed!");
            }else {
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}