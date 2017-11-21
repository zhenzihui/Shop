package User;

import Common.Model;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import shop.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
@WebServlet(name = "AuthController")
public class AuthController extends HttpServlet {
    User user;
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String method = request.getParameter("method");
            switch (method)
            {
                case "create":
                   boolean flag= createAccount(request.getParameter("name"),
                            request.getParameter("password"),
                            request.getParameter("role"));


                   if(flag)
                   {
                       Statement stmt = Model.connect();
                       try {
                          ResultSet set= stmt.executeQuery("select id from users where name='"+user.getName()+"'");
                          while (set.next())
                          {
                              int id=set.getInt("id");
                              user.setId(id);
                          }
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }


                       session =request.getSession();
                       session.setAttribute("login",user);
                       response.sendRedirect("/");
                   }

                    break;
                case "login":
                    int id=Integer.parseInt(request.getParameter("id"));
                    String password=request.getParameter("password");
                   if((user=login(id,password,request.getParameter("role")))==null)
                   {
                       request.setAttribute("alert","alert('账号或密码错误')");
                       request.getRequestDispatcher("/login.jsp").forward(request,response);

                   }
                    session=request.getSession();
                    session.setAttribute("login",user);
                    response.sendRedirect("/");
                    break;
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        session=request.getSession();
        switch (method)
        {
            case "logout":
                logout(session);
                response.sendRedirect("/");

                break;
            case "show":
                int uid =((User)session.getAttribute("login")).getId();
                List<Product> products = Cart.all(uid);
                List<Product> history = History.all(uid,"desc");
                request.setAttribute("history",history);
                request.setAttribute("items",products);
                request.getRequestDispatcher("/UserDetail.jsp").forward(request,response);

                break;
        }

    }
    boolean createAccount(String name,String password,String role)
    {
            user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setRole(role);
            return user.save();
    }
    User login(int id,String password,String role)
    {
        user= new User();
        ResultSet set = User.Find("users",id);
        try {
            if(set.next())
            {
                System.out.print("有记录\n"+set.toString());
                user.setPassword(set.getString("password"));
                user.setId(set.getInt("id"));
                if(user.getId()==id&&password.equals(user.getPassword()))
                {
                    user.setId(set.getInt("id"));
                    user.setName(set.getString("name"));
                    user.setRole(set.getString("role"));
                    return user;
                }

            }else
            {
                return null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    void logout(HttpSession session)
    {
        session.setAttribute("login",null);
    }

}
