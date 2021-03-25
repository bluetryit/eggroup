package login;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.administrator.model.AdministratorService;
import com.administrator.model.AdministratorVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.res.model.ResService;
import com.res.model.ResVO;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    // 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
    // 【實際上應至資料庫搜尋比對】
    protected ResVO allowRes(String res_ac, String res_pass)
    { // 會員

        ResService rs = new ResService();
        ResVO resVO = rs.resFindByAC(res_ac);
        if (resVO != null && res_pass.equals(resVO.getRes_pass()))
            return resVO;
        else
            return null;
    }

    protected MemVO allowUser(String member_id, String mem_pwd)
    {

        MemService ms = new MemService();
        MemVO memberVO = ms.memFindByAC(member_id);
        if (memberVO != null && mem_pwd.equals(memberVO.getMem_pass()))
            return memberVO;
        else
            return null;
    }
    
    protected AdministratorVO allowAdmin(String adminAccount, String adminPass)
    {

        AdministratorService administratorService = new AdministratorService();
        AdministratorVO adminVO = administratorService.getOneAdminByAccount(adminAccount);
        
        if (adminVO != null && adminPass.equals(adminVO.getAdmin_pass()))
            return adminVO;
        else
            return null;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        
        if ("memLogin".equals(action))
        {
            // 【取得使用者 帳號(account) 密碼(password)】
            String member_id = req.getParameter("member_id");
            String mem_pwd = req.getParameter("mem_pwd");

            // 【檢查該帳號 , 密碼是否有效】
            MemVO memberVO = allowUser(member_id, mem_pwd);

            Map<String, String> errorMsgsForLogin = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgsForLogin", errorMsgsForLogin);
            
            if ((memberVO) == null)
            { // 【帳號 , 密碼無效時】
                errorMsgsForLogin.put("member", "帳號或密碼輸入錯誤");
                req.setAttribute("login", "false");
                req.getRequestDispatcher("hometag.jsp").forward(req, res);
            }
            else
            { // 【帳號 , 密碼有效時, 才做以下工作】
                
                session.setAttribute("memberVO", memberVO); // *工作1: 才在session內做已經登入過的標識

                res.sendRedirect(req.getContextPath() + "/hometag.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
            }
        }

        if ("resLogin".equals(action))
        {

            // 【取得使用者 帳號(account) 密碼(password)】
            String res_ac = req.getParameter("res_ac");
            String res_pass = req.getParameter("res_pass");

            // 【檢查該帳號 , 密碼是否有效】
            ResVO resVO = allowRes(res_ac, res_pass);

            Map<String, String> errorMsgsForLogin = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgsForLogin", errorMsgsForLogin);

            if ((resVO) == null)
            { // 【帳號 , 密碼無效時】
                errorMsgsForLogin.put("resMember", "帳號或密碼輸入錯誤");
                session.setAttribute("resLogin", "false");
                req.getRequestDispatcher("hometag.jsp").forward(req, res);
            }
            else
            { // 【帳號 , 密碼有效時, 才做以下工作】
                session.setAttribute("resVO", resVO); // *工作1: 才在session內做已經登入過的標識

                res.sendRedirect(req.getContextPath() + "/hometag.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
            }
        }
        
        if ("adminLogin".equals(action))
        {
            // 【取得使用者 帳號(account) 密碼(password)】
            String adminAccount = req.getParameter("adminAccount");
            String adminPass = req.getParameter("adminPass");

            // 【檢查該帳號 , 密碼是否有效】
            AdministratorVO adminVO = allowAdmin(adminAccount, adminPass);

            Map<String, String> errorMsgsForLogin = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgsForLogin", errorMsgsForLogin);
            
            if ((adminVO) == null)
            { // 【帳號 , 密碼無效時】
                errorMsgsForLogin.put("adminAccount", "帳號或密碼輸入錯誤");
                req.getRequestDispatcher("/back-end/adminLogin.jsp").forward(req, res);
            }
            else
            { // 【帳號 , 密碼有效時, 才做以下工作】
                
                session.setAttribute("adminVO", adminVO); // *工作1: 才在session內做已經登入過的標識
                
                res.sendRedirect(req.getContextPath() + "/back-end/BackHome.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
            }
        }
    }
}