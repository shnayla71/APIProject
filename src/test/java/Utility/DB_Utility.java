package Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class DB_Utility {
    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmnt;
    // WE want to store certain row data as a map
    // entire row number map<string,string>


    /*
    getting singke column cell value at certain row
    row 2 column 3
     */
    public static String getColumnDataAtRow(int rowNum,int columnIndex){

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnIndex ) ;
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }
        return result ;
    }

    public static String getColumnDataAtRow(int rowNum,String columnName){

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnName ) ;
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }
        return result ;
    }
    /*
    a ststic method create connection
    with valid url username and password
     */
    public static List<String> getRowDataAsList(int rowNum){

        List<String> rowDataList=new ArrayList<>();

        try {
            rs.absolute(rowNum);
            for(int i=1;i<=getColumnCNT();i++){
                rowDataList.add(rs.getString(i));
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println(" error getdataList");
            e.printStackTrace();
        }


        return rowDataList;
    }
    public static List<String> getColumnDataAsList(int columnIndex){
        List<String> columnDataList=new ArrayList<>();

        try {
            rs.beforeFirst();
            while( rs.next()){
                columnDataList.add(rs.getString(columnIndex));
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println(" error column number");
            e.printStackTrace();
        }


        return columnDataList;
    }
    public static List<String> getColumnDataAsList(String columnName){
        List<String> columnDataList=new ArrayList<>();

        try {
            rs.beforeFirst();
            while( rs.next()){
                String data=rs.getString(columnName);
                columnDataList.add(data);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println(" error column number");
            e.printStackTrace();
        }


        return columnDataList;
    }
    public static void createConnection(){
        String connectionStr=ConfigurationReader.getProperty("database.url");
        String username=ConfigurationReader.getProperty("database.username");
        String password=ConfigurationReader.getProperty("database.password");

        try {
            conn = DriverManager.getConnection(connectionStr,username,password);
            System.out.println(" connection succesfull");
        } catch (SQLException e) {
            System.out.println(" connection has failed");
            e.printStackTrace();
        }

    }
    public static void createConnection(String env){
        System.out.println("welcome your envoirment!");
        String connectionStr=ConfigurationReader.getProperty(env+".database.url");
        String username=ConfigurationReader.getProperty(env+".database.username");
        String password=ConfigurationReader.getProperty(env+".database.password");

        createConnection(connectionStr,username,password);
    }
    public static void createConnection(String url,String username,String password){
        try {
            conn = DriverManager.getConnection(url,username,password);
            System.out.println(" connection succesfull");
        } catch (SQLException e) {
            System.out.println(" connection has failed");
            e.printStackTrace();
        }

    }
    public static ResultSet runQuery(String query){

        try {
            Statement stmnt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmnt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /*
     A method for column count
     */
    public static int getColumnCNT(){
        int countColumn=0;

        try {

            ResultSetMetaData   rsmd = rs.getMetaData();
            countColumn=rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("error while counting the columns ");
            e.printStackTrace();
        }

        return countColumn;
    }
    public static int getRowCount(){
        int rowCount=0;

        try {
            rs.last();
            rowCount=rs.getRow();

            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("error while counting the row count ");
            e.printStackTrace();
        }

        return rowCount;
    }
    /*
    a method to display all the data in the result set
     */
    public static void displayAllData() {
        int colcount = DB_Utility.getColumnCNT();


        try {
            //in order the start from begining
            rs.beforeFirst();
            while (rs.next() == true) {
                for (int i = 1; i <= colcount; i++) {
                    System.out.println(rs.getString(i));
                }
            }
            //now the cursor is the after last location
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println(" error whilw getting all data");
            e.printStackTrace();
        }
    }
    public static void getQueryResultMap(String query) {
        runQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                List< Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(rs.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void getRowMap(String query) {
        getQueryResultMap(query);
    }


    public static void destroy() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmnt != null) {
                stmnt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close database connection!");
        }
    }

    public static Map<String,String> getRowMap( int rowNum ){

        Map<String,String> rowMap =  new LinkedHashMap<>() ; //new HashMap<>();
        try{

            rs.absolute(rowNum);

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCNT() ; colNum++) {
                String colName = rsmd.getColumnName( colNum );
                String colValue= rs.getString( colNum ) ;
                rowMap.put(colName, colValue);
            }
            rs.beforeFirst();

        }catch (SQLException e){
            System.out.println("ERRROR AT ROW MAP FUNCTION");
        }

        return rowMap;
    }
}


