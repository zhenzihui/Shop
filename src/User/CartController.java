package User;

import netscape.javascript.JSObject;
import org.apache.commons.io.output.WriterOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/4/3.
 */
@WebServlet(name = "CartController")
public class CartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String method = request.getParameter("method");
        int uid,pid;
        uid=Integer.parseInt(request.getParameter("uid"));
        pid=Integer.parseInt(request.getParameter("pid"));
        PrintWriter out = response.getWriter();
    switch (method)
    {
        case "add":


            if(add(uid,pid))
            {

                out.print("{'msg':true}");

            }else
            {
                out.print("{'msg':false}");
            }

            break;
        case "rm":
            Cart.Destory(uid,pid);

            break;


    }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    boolean add(int uid,int pid)
    {
        Cart cart = new Cart();
        cart.setUserId(uid);
        cart.setpId(pid);
        return cart.save();

    }

}
