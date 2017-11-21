package shop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import User.*;
/**
 * Created by Administrator on 2017/4/4.
 */
@WebFilter(filterName = "DetailFilter")
public class DetailFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        User user;
        HttpSession session = request.getSession();
        user=(User)session.getAttribute("login");
        if(user!=null)
        {
            int uid =user.getId();
            int pid = Integer.parseInt(request.getParameter("id"));

            History history = new History();
            history.setPid(pid);
            history.setUid(uid);
            history.save();


            chain.doFilter(req, resp);
        }
        else
            {
                response.sendRedirect("/register.jsp");
            }



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
