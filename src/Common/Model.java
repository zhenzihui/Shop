package Common;
import java.sql.*;
public class Model {
    public static Connection getConnection()
    {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        String username="root";
        String password="";
        String url="jdbc:mysql://localhost:3306/shop?user=" +
                username+"&password=" +
                password;
        try {
            con = DriverManager.getConnection(url);
            return con;

        }catch (SQLException e) {
            System.out.print("语句不行");
        }
        return null;

    }


    public   static Statement connect()
    {
        Connection con;
        Statement stmt;



        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        String username="root";
        String password="";
        String url="jdbc:mysql://localhost:3306/shop?user=" +
                username+"&password=" +
                password;
        try {
            con = DriverManager.getConnection(url);


            stmt = con.createStatement();
            return stmt;

        }catch (SQLException e) {
            System.out.print("语句不行");
        }
        return null;
    }
    public static ResultSet Find(String table, int id)
    {
        ResultSet set=null;
        Connection conn = getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from "+ table +" where id=?");
            statement.setInt(1,id);
            set=  statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }



}
