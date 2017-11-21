package User;

import Common.Model;
import shop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public class History extends Model {
    int id,uid,pid;
    long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean save()
    {
        Connection connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT into history(uid,pid) VALUES(?,?)");
            stmt.setInt(1,getUid());
            stmt.setInt(2,getPid());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static List<Product> all(int uid,String order)
    {
        Statement statement = connect();
        List<Product> products = new ArrayList<>();
        try {
        ResultSet set = statement.executeQuery("select * from history where uid="+uid+" order by see_at "+order);
        while (set.next())
        {
           int pid = set.getInt("pid");
           Product product = Product.find(pid);
           if(product!=null)
           {
               products.add(product);
           }
        }
        return products;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
