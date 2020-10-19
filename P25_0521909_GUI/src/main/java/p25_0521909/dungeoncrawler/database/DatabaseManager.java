package p25_0521909.dungeoncrawler.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ludmi
 */
public class DatabaseManager {
    private static final String URL = "jdbc:derby:DungeonCrawlerDB;create=true";
    private static final String USERNAME = "p25";
    private static final String PASSWORD = "0521909";

    private static Connection connection;
    private static Statement statement;
    
    private static String tableName, rowName; 
    
    private DatabaseManager(){}

    public static void connectToDatabase(){
        try{
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean checkTableExists(String tableName){
        boolean tableExists = false;
        
        try{
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet resultSet = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while(resultSet.next()){
                String resultTableName = resultSet.getString("TABLE_NAME");
                
                if(resultTableName.equalsIgnoreCase(tableName)){
                    tableExists = true;
                    break;
                }
            }
        }
        
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return tableExists;
    }
    
    public static void setSearchTableName(String value){
        tableName = value;
    }
    
    public static void setSearchRowName(String value){
        rowName = value;
    }
    
    public static int getIntValue(String columnName){
        int value = 0;
        ResultSet resultSet = getDataRow();
        
        try{
            if(resultSet.next()){
                value = resultSet.getInt(columnName);
            } 
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        return value;
    }
    
    public static double getDoubleValue(String columnName){
        double value = 0.0;
        ResultSet resultSet = getDataRow();
        
        try{
            if(resultSet.next()){
                value = resultSet.getDouble(columnName);
            } 
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        return value;
    }
    
    public static String getStringValue(String columnName){
        String value = "";
        ResultSet resultSet = getDataRow();

        try{
            if(resultSet.next()){
                value = resultSet.getString(columnName);
            } 
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        return value;
    }
    
    private static ResultSet getDataRow(){
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT * FROM P25." + tableName + " WHERE INDEX = " + rowName;
            resultSet = statement.executeQuery(query);           
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultSet;
    }
}
