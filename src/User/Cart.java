package User;

import Common.Model;
import shop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
public class Cart extends Model {
    int id;
    int userId;
    int pId;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public boolean save()
    {
        try {
            PreparedStatement statement = getConnection().prepareStatement("insert into cart(userid,pid) values(?,?);");
            statement.setInt(1,userId);
            statement.setInt(2,pId);
            statement.execute();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;
    }



    public static List<Product> all(int uid)
    {
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connect();
            ResultSet set= statement.executeQuery("SELECT pid from cart WHERE userid="+uid);
            while (set.next())
            {
             Product product=   Product.find(set.getInt("pid"));
             if(product!=null){
                 products.add(product);
             }

            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public static boolean Destory(int uid,int pid)
    {
        Statement statement=connect();


        try {
            statement.execute("delete from cart WHERE userid="+uid+" and pid="+pid);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;




}
}
