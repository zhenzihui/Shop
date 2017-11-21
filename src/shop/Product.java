package shop;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class Product extends Common.Model {
    private int id;
    private String name;
    private String city;
    private float price;
    private int number;
    private String imgpath;
   static private String table="products";
private Statement stmt;



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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public boolean save()
    {
        stmt=connect();
        String sql="insert into products(name,city,price,number,img) values('" +
                getName()+"','" +
                getCity()+"'," +
                getPrice()+",'" +
                getNumber()+"','" +
                getImgpath()+"')";
        System.out.print(sql);
        try {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





    public static Product find(int id)
    {
      String name,city,img;
      float price;
      int number;

       String getname = "select * from "+table+" where id=" +id;
        try {
            Statement stmt=connect();
            ResultSet set= stmt.executeQuery(getname);
            Product product=new Product();
            if(set.next())
            {
              name=  set.getString("name");
              city=  set.getString("city");
              price=  set.getFloat("price");
              number=  set.getInt("number");
              img=  set.getString("img");
               product.setCity(city);
               product.setName(name);
               product.setId(id);
               product.setNumber(number);
               product.setPrice(price);
               product.setImgpath(img);
                return product;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Product> all()
    {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from "+table;
        Statement stmt=connect();
        try {
            ResultSet set= stmt.executeQuery(sql);
            while(set.next())
            {
                Product product = new Product();
                product.setCity(set.getString("city"));
                product.setName(set.getString("name"));
                product.setId(set.getInt("id"));
                product.setNumber( set.getInt("number"));
                product.setPrice(set.getFloat("price"));
                product.setImgpath(set.getString("img"));
                productList.add(product);

            }
            return productList;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
