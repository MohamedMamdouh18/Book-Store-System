package Database;

import java.sql.*;
public class DBConnector {
    public static Connection connect_to_db(){
        String dbname = "db.bsxjvkxbhtvquhjoeweo.supabase.co:5432/postgres";
        String user = "Manager";
        String pass = "123456789";
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://"+dbname,user,pass);
            if(conn!=null)
                System.out.println("Connection Established");
            else
                System.out.println("Connection Failed");

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
