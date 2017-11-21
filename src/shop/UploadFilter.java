package shop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import User.User;
import com.sun.deploy.net.HttpResponse;

/**
 * Created by Administrator on 2017/4/2.
 */
@WebFilter(filterName = "UploadFilter")
public class UploadFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = ((HttpServletRequest)req);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("login");
        if(user!=null&&user.getRole().equals("admin"))
        {
            chain.doFilter(req, resp);
        }
        else
            {
                HttpServletResponse response = ((HttpServletResponse)resp);
                request.setAttribute("alert","你所在的用户组无法上传商品");
                request.getRequestDispatcher("/").forward(request,response);

            }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
