package User;

import java.sql.*;

/**
 * Created by Administrator on 2017/3/30.
 */
public class User extends Common.Model {
Connection conn;
    int id;
String name,role,password;
static String table = "users";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public boolean save()
    {
        conn=getConnection();
        try {
            PreparedStatement statement = getConnection().prepareStatement("insert into users(name,password,role) values(?,?,?)");
            statement.setString(1,getName());
            statement.setString(2,getPassword());
            statement.setString(3,getRole());
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet find(int id)
    {
        ResultSet set=null;
        Connection conn = getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from users where id=?");
            statement.setInt(1,id);
            set=  statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

}
