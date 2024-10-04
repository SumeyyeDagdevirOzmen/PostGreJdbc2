import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaStaffBirthDay {
    @Test
    public void Test() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of birth_date column from "staff" table
        String sql1 = "SELECT birth_date FROM staff";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<String> birth_date = new ArrayList<>();
        while (resultSet1.next()){
            birth_date.add(resultSet1.getString(1));
        }
        System.out.println("birth_date = " + birth_date);
        //Assert that there are some staff birth_date "2022-12-03 23:00:00".
        Assert.assertTrue(birth_date.contains("2022-12-03 23:00:00"));

//        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();
    }
}
