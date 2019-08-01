package timduk.leonid;

import timduk.leonid.utils.log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase
{
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;


    //TODO reconnect
    DataBase() throws ClassNotFoundException, SQLException
    {
        log.info("Database begin to initialize");
        String db_server = "92.63.203.106";
        String db_username = "hybrid_hybrid";
        String db_password = "52fgrefVyK";
        String db_name = "hybrid_bot";

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(String.format(
                "jdbc:mysql://%s:3306/%s?user=%s&password=%s",
                db_server,
                db_name,
                db_username,
                db_password
        ));
        log.info("Connected to " + db_server + ", database - " + db_name + ", as " + db_username);
        log.info("Database initialized");
    }

    //SELECT * from feedback.comments WHERE col_name = 10
    public List<List> select(String what, String from) throws SQLException
    {
        List<List> result = new ArrayList<>();
        String SQL = String.format("SELECT %s FROM %s", what, from);
        preparedStatement = con.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();
        resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next())
        {
            List<String> localArr = new ArrayList<>();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++)
            {
                localArr.add(resultSet.getString(column));
            }
            result.add(localArr);
        }
        return result;
    }


    public List<List> select(String what, String from, String where) throws SQLException
    {
        List<List> result = new ArrayList<>();
        String SQL = String.format("SELECT %s FROM %s WHERE %s", what, from, where);
        preparedStatement = con.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();
        resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next())
        {
            List<String> localArr = new ArrayList<>();
            for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++)
            {
                localArr.add(resultSet.getString(column));
            }
            result.add(localArr);
        }
        return result;
    }

    public Boolean insert(String tableName, String columnsName, String values) throws SQLException
    {
        String SQL = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columnsName, values);
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.executeUpdate() > 0;
    }

    public Boolean insert(String tableName, String columnsName, List<String> values) throws SQLException
    {
        StringBuilder string = new StringBuilder();
        for (String value : values)
        {
            string.append("(").append(value).append("),");
        }
        String SQL = String.format("INSERT INTO %s (%s) VALUES %s", tableName, columnsName, string.toString().substring(0, string.length() - 1));
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.executeUpdate() > 0;
    }

    public Boolean update()
    {
        return false;
    }

    public Boolean delete(String tableName, String where) throws SQLException
    {
        String SQL = String.format("DELETE FROM %s WHERE %s", tableName, where);
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.execute();
    }

    public Boolean delete(String tableName) throws SQLException
    {
        String SQL = String.format("DELETE FROM %s", tableName);
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.execute();
    }

    public Boolean createTable(String tableName, String fields, String engine) throws SQLException
    {
        String SQL = String.format("CREATE TABLE %s (%s) ENGINE=%s", tableName, fields, engine);
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.executeUpdate() > 0;
    }

    public Boolean createTable(String tableName, String fields) throws SQLException
    {
        String SQL = String.format("CREATE TABLE %s (%s) ENGINE=InnoDB", tableName, fields);
        preparedStatement = con.prepareStatement(SQL);
        return preparedStatement.executeUpdate() > 0;
    }

    public Boolean showTables(String tableName) throws SQLException
    {
        String SQL = String.format("SHOW TABLES LIKE '%s'", tableName);
        preparedStatement = con.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}