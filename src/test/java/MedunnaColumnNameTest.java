import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaColumnNameTest {
    @Test
    public void medunnaTest() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the columns of room table
        String sql1 = "SELECT * FROM room";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        ResultSetMetaData rsMetaData = resultSet1.getMetaData();
        System.out.println("List of column names in the current table: ");
        //Retrieving the list of column names
        List<String> columnName = new ArrayList<>();
        int count = rsMetaData.getColumnCount();
        for(int i = 1; i<=count; i++) {
            System.out.println("columnName : "+rsMetaData.getColumnName(i));
            columnName.add(rsMetaData.getColumnName(i));
        }
        System.out.println(columnName);
        Assert.assertTrue(columnName.contains("room_type"));
        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();
    }
}
