import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaPatientTest {
    @Test
    public void Test() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of patient_id column from "appointment" table
        String sql1 = "SELECT patient_id FROM appointment";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<String> patient_id = new ArrayList<>();
        while (resultSet1.next()){
            patient_id.add(resultSet1.getString(1));
        }
        System.out.println("patient_id = " + patient_id);
        //Assert that there are some appointment patient_id "405892".
        Assert.assertTrue(patient_id.contains("405892"));
        //patient ların 20295 tane oldugunu dogrulayin
        Assert.assertEquals(20295, patient_id.size());
        System.out.println("size : "+patient_id.size());
        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();
    }
}
