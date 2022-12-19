import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandirilir.
    SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Goldfish.94");
        Statement st = con.createStatement();


        //CallableStatement ile function cagirmayi parametrelendirecegiz.

        //1.Adim:Function kodunu yaz:
        String sql1 = "CREATE OR  REPLACE FUNCTION  toplamaF(x NUMERIC,y NUMERIC)\n" +
                " RETURNS NUMERIC\n" +
                " LANGUAGE plpgsql\n" +
                " AS\n" +
                " $$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN x+y;\n" +
                " \n" +
                " END\n" +
                " $$";

        //2.Adim:Function'i calistir
        st.execute(sql1);

        //3.Adim: Function'i cagir.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}");//ilk parametre yani ilk ? isareti return type.

        //4.Adim: Return icin registerOutParameter() methodunu, parametreler icin ise set() ...methodlarini uygula.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);


        //5.Adim: execute() methodu ile CallableStatement'i calistir.
        cst1.execute();

        //6.Adim: Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.KOninin hacmi:pirekare*yukseklik/3 ===>pi*r^2*h/3


        //1.Adim:Function kodunu yaz:
        String sql2 = "CREATE OR   REPLACE FUNCTION  konininHacmiF(r NUMERIC,h NUMERIC)\n" +
                " RETURNS NUMERIC\n" +
                " LANGUAGE plpgsql\n" +
                " AS\n" +
                " $$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN 3.14*r*r*h/3;\n" +
                " \n" +
                " END\n" +
                " $$";

        //2.Adim:Function'i calistir
        st.execute(sql2);

        //3.Adim: Function'i cagir.
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmiF(?,?)}");

        //4.Adim: Return icin registerOutParameter() methodunu, parametreler icin ise set() ...methodlarini uygula.
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);

        //5.Adim: execute() methodu ile CallableStatement'i calistir.
        cst2.execute();

        //6.Adim: Sonucu cagirmak icin return data type tipine gore
        System.out.printf("%.2f", cst2.getBigDecimal(1));


        String sql3= "CREATE OR  REPLACE FUNCTION  toplama2F(x NUMERIC,y NUMERIC,z NUMERIC)\n" +
                " RETURNS NUMERIC\n" +
                " LANGUAGE plpgsql\n" +
                " AS\n" +
                " $$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN x+y+z;\n" +
                " \n" +
                " END\n" +
                " $$";


        st.execute(sql3);
        CallableStatement cst4 = con.prepareCall("{? = call toplama2F(?,?,?)}");

        cst4.registerOutParameter(1, Types.NUMERIC);
        cst4.setInt(2, 1);
        cst4.setInt(3, 6);
        cst4.setInt(4, 3);

        cst4.execute();

        System.out.println( cst4.getBigDecimal(1));

        String sql6="CREATE OR  REPLACE FUNCTION  concatF(a varchar(10),b varchar(10))\n" +
                "                 RETURNS varchar\n" +
                "                 LANGUAGE plpgsql\n" +
                "                 AS\n" +
                "                 $$\n" +
                "                 BEGIN \n" +
                "                 \n" +
                "                 RETURN concat(a,b); \n" +
                "                \n" +
                "                 END\n" +
                "                 $$;";

        st.execute(sql6);
        CallableStatement cst5 = con.prepareCall("{? = call concatF(?,?)}");
        cst5.registerOutParameter(1,Types.VARCHAR);
        cst5.setObject(2,"ALi");
        cst5.setObject(3,"Can");
        cst5.execute();
        System.out.println("cst5 = " + cst5.getString(1));







/*
System.out.println("koninin hacmi: " + String.format("%.2f",cst2.getBigDecimal(1)));
 eğer printfsiz yapmak isterseniz de böyle
 */


    }

}