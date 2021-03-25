package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilterForBackEnd implements Filter
{

    private FilterConfig config;

    public void init(FilterConfig config)
    {
        this.config = config;
    }

    public void destroy()
    {
        config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws ServletException, IOException
    {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 【取得 session】
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        // 【從 session 判斷此user是否登入過】
        Object BackAccount = session.getAttribute("adminVO");


        System.out.println("BackAccount = " + BackAccount);

        
        System.out.println("req.getRequestURI() = " + req.getRequestURL());
        System.out.println("req.getHeader(\"referer\") = " + req.getHeader("referer"));


        
        if (BackAccount != null)
        {
            chain.doFilter(request, response);
        }
        else
        {
            res.sendRedirect(req.getContextPath() + "/back-end/adminLogin.jsp");
            return;

        }
    }
}