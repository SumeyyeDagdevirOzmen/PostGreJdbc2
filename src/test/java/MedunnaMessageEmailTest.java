import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaMessageEmailTest {
    @Test
    public void medunnaTest() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of email column from "cmessage" table
        String sql1 = "SELECT email FROM cmessage";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<String> patient_id = new ArrayList<>();
        while (resultSet1.next()){
            patient_id.add(resultSet1.getString(1));
        }
        System.out.println("patient_id = " + patient_id);
        //Assert that there are some cmessage email "zeynep05@gmail.com".
        Assert.assertTrue(patient_id.contains("zeynep05@gmail.com"));
        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();
    }

}
