package Mail;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fooditem.model.FooditemService;
import com.fooditem.model.FooditemVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

/**
 * Servlet implementation class MailServlet
 */
@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();

		if ("insert".equals(action)) { // 來自addMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// String mem_no = req.getParameter("mem_no");

				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//					if (mem_name == null || mem_name.trim().length() == 0) {
//						errorMsgs.add("員工姓名: 請勿空白");
//					} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//		            }

				System.out.println(mem_name);

				String mem_adrs = req.getParameter("mem_adrs").trim();
				if (mem_adrs == null || mem_adrs.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String mem_sex = req.getParameter("mem_sex").trim();
				if (mem_sex == null || mem_sex.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				java.sql.Date mem_bd = null;
				try {
					mem_bd = java.sql.Date.valueOf(req.getParameter("mem_bd").trim());
				} catch (IllegalArgumentException e) {
					mem_bd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String mem_ph = req.getParameter("mem_ph").trim();
				if (mem_ph == null || mem_ph.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				Integer mem_point = new Integer(req.getParameter("mem_point").trim());

				Part mem_img = req.getPart("mem_img");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				System.out.println(1);
				// inputStream轉byte[]
				byte[] data = new byte[mem_img.getInputStream().available()];
				BufferedInputStream buffer = new BufferedInputStream(mem_img.getInputStream());
				mem_img.getInputStream().read(data, 0, data.length);

				System.out.println(2);
				String mem_pass = req.getParameter("mem_pass").trim();
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_ac = req.getParameter("mem_ac").trim();
				if (mem_ac == null || mem_ac.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_intro = req.getParameter("mem_intro").trim();
				if (mem_intro == null || mem_intro.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_status = req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				String mem_no = req.getParameter("mem_no");
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_adrs(mem_adrs);
				memVO.setMem_sex(mem_sex);
				memVO.setMem_bd(mem_bd);
				memVO.setMem_ph(mem_ph);
				memVO.setMem_email(mem_email);
				memVO.setMem_point(mem_point);
				memVO.setMem_img(data);
				memVO.setMem_pass(mem_pass);
				memVO.setMem_ac(mem_ac);
				memVO.setMem_intro(mem_intro);
				memVO.setMem_status(mem_status);

				MemService memSrv = new MemService();
				memSrv.memInsert(mem_name, mem_adrs, mem_sex, mem_bd, mem_ph, mem_email, mem_point, data, mem_pass,
						mem_ac, mem_intro, mem_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/MEM-INF/addMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemService memSvc = new MemService();
				// memVO = memSvc.memInsert();

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/MEM-INF/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);
				
				String to = req.getParameter("e_mail");
				String mem = req.getParameter("memName");
				String subject = "您好，你已經完成註冊，請您進行會員驗證！";
				String messageText = mem + "您已經驗證成功，祝您在EGG玩的愉快";
				MailService mailSvc = new MailService();
				mailSvc.sendMail(to, subject, messageText);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/MEM-INF/addMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}