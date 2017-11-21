package shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/3/30.
 */
@WebServlet(name = "ImgController")
public class ImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String path = Product.find(id).getImgpath();
            System.out.print("图片路径："+path);
            FileInputStream inputStream = new FileInputStream(path);
            int size = inputStream.available();
            byte img[] = new byte[size];
            inputStream.read(img);
            inputStream.close();
            response.setContentType("image/*");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(img);
            outputStream.flush();
            outputStream.close();
        }catch(Exception e)
        {
            System.out.print("打开图片出错");
        }


    }
}
