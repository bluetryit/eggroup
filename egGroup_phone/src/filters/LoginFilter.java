package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter
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
        System.out.println("LoginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 【取得 session】
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        // 【從 session 判斷此user是否登入過】
        Object account = session.getAttribute("memberVO");
        Object resAccount = session.getAttribute("resVO");

        System.out.println("account = " + account);
        System.out.println("resAccount = " + resAccount);
        
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getHeader(\"referer\") = " + req.getHeader("referer"));

        
        if (account != null)
        {
            chain.doFilter(request, response);

        }
        else if (resAccount != null && !req.getRequestURL().toString().contains("mem"))
        {
            chain.doFilter(request, response);
        }
        else
        {

            if (req.getAttribute("location") == null)
            {
                String location = req.getHeader("referer");
                if (location == null || location.endsWith(".do"))
                {
                    session.setAttribute("location", req.getHeader("referer"));
                }
                else
                {
                    session.setAttribute("location", req.getRequestURL());
                }
            }
            session.setAttribute("login", "false");
            res.sendRedirect(req.getContextPath() + "/hometag.jsp");
            return;

        }
    }
}