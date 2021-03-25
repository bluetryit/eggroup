package com.post.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemVO;
import com.post.model.*;
import com.res.model.*;

@MultipartConfig()
public class PostServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        
        System.out.println("action" + action);
        if ("getAll".equals(action)) {
            // 查詢
            PostDAO dao = new PostDAO();
            List<PostVO> list = dao.getAll();
            session.setAttribute("list", list);
            String url = "/post/listAllPost.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
            successView.forward(req, res);
            return;
        }
        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
                String str = req.getParameter("post_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("請輸入貼文編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/post/postselectpage.jsp");
                    failureView.forward(req, res);
                    return;// 程式中斷
                }

                String post_no = null;
                try {
                    post_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("貼文編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/post/postselectpage.jsp");
                    failureView.forward(req, res);
                    return;// 程式中斷
                }

                /*************************** 2.開始查詢資料 *****************************************/
                PostService PostService = new PostService();
                PostVO PostVO = PostService.getOnePost(post_no);
                if (PostVO == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/post/postselectpage.jsp");
                    failureView.forward(req, res);
                    return;// 程式中斷
                }

                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
                req.setAttribute("PostVO", PostVO); // 資料庫取出的empVO物件,存入req
                String url = "/post/listOnePost.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/post/postselectpage.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 ****************************************/
                String post_no = new String(req.getParameter("post_no"));

                /*************************** 2.開始查詢資料 ****************************************/
                PostService PostService = new PostService();
                PostVO PostVO = PostService.getOnePost(post_no);

                /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
                req.setAttribute("PostVO", PostVO); // 資料庫取出的empVO物件,存入req
                String url = "/post/updatepostinput.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/post/listAllPost.jsp");
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

//          try {
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String post_no = new String(req.getParameter("post_no").trim());
            
            String post_memno = req.getParameter("post_memno");
//              String post_memnoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (post_memno == null || post_memno.trim().length() == 0) {
                errorMsgs.add("會員編號: 請勿空白");
            }

            String post_res_no = req.getParameter("post_res_no").trim();
            if (post_res_no == null || post_res_no.trim().length() == 0) {
                errorMsgs.add("餐廳編號請勿空白");
            }

            String post_text = req.getParameter("post_text").trim();
            if (post_text == null || post_text.trim().length() == 0) {
                errorMsgs.add("貼文內容請勿空白");
            }

            Part part = req.getPart("post_img");
            InputStream is = part.getInputStream();
            byte[] post_img = new byte[is.available()];
            is.read(post_img);
            is.close();

            Timestamp post_time = null;
//              try {
//                  post_time = Timestamp.valueOf(req.getParameter("post_time").trim()+" 00:00:00");
            Date date = java.sql.Date.valueOf(req.getParameter("post_time").trim());
//                  System.out.println(java.sql.Date.valueOf(req.getParameter("post_time").trim()));
            post_time = new Timestamp(System.currentTimeMillis());
//              }catch (IllegalArgumentException e) {
//                  post_time=new Timestamp(System.currentTimeMillis());
//                  errorMsgs.add("請輸入日期!");
//              }

            if (post_text == null || post_text.trim().length() == 0) {
                errorMsgs.add("貼文內容請勿空白");
            }

            String post_respon = req.getParameter("post_respon").trim();
            if (post_respon == null || post_respon.trim().length() == 0) {
                errorMsgs.add("餐廳回覆內容請勿空白");
            }

            Integer post_rate = new Integer(req.getParameter("post_rate").trim());

            String post_status = req.getParameter("post_status").trim();
            if (post_status == null || post_status.trim().length() == 0) {
                errorMsgs.add("貼文狀態內容請勿空白");
            }

            PostService postSvc = new PostService();
            postSvc.updatePost(post_no, post_memno, post_res_no, post_text, post_img, post_time, post_respon, post_rate,
                    post_status);

            PostVO PostVO = new PostVO();
            PostVO.setPost_no(post_no);
            PostVO.setPost_memno(post_memno);
            PostVO.setPost_res_no(post_res_no);
            PostVO.setPost_text(post_text);
            PostVO.setPost_img(post_img);
            PostVO.setPost_time(post_time);
            PostVO.setPost_respon(post_respon);
            PostVO.setPost_rate(post_rate);
            PostVO.setPost_status(post_status);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("PostVO", PostVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/post/updatepostinput.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            PostService PostService = new PostService();
            PostVO = PostService.updatePost(post_no, post_memno, post_res_no, post_text, post_img, post_time,
                    post_respon, post_rate, post_status);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("PostVO", PostVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/post/listOnePost.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);

            /*************************** 其他可能的錯誤處理 *************************************/
//          } catch (Exception e) {
//              errorMsgs.add("修改資料失敗:"+e.getMessage());
//              RequestDispatcher failureView = req
//                      .getRequestDispatcher("/post/updatepostinput.jsp");
//              failureView.forward(req, res);
//          }
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求
            System.out.println(123);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            
            MemVO memVO = (MemVO) session.getAttribute("memberVO");
            String post_memno = memVO.getMem_no();
            System.out.println("post_memno" + post_memno);
            String post_res_no = req.getParameter("post_res_no");
System.out.println("post_res_no" + post_res_no);
            String post_text = req.getParameter("post_text").trim();
            if (post_text == null || post_text.trim().length() == 0) {
                errorMsgs.add("貼文內容請勿空白");
            }
            System.out.println("post_text" + post_text);
            Part part = req.getPart("post_img");
            InputStream is = part.getInputStream();
            byte[] post_img = new byte[is.available()];
            is.read(post_img);

            Timestamp post_time = null;

            post_time = new Timestamp(System.currentTimeMillis());

            if (post_text == null || post_text.trim().length() == 0) {
                errorMsgs.add("貼文內容請勿空白");
            }
System.out.println("post_time" + post_time);
            Integer post_rate = new Integer(req.getParameter("post_rate").trim());
System.out.println("post_rate" + post_rate);
            String post_status = "post1";

            PostVO PostVO = new PostVO();

            PostVO.setPost_memno(post_memno);
            PostVO.setPost_res_no(post_res_no);
            PostVO.setPost_text(post_text);
            PostVO.setPost_img(post_img);
            PostVO.setPost_time(post_time);
            PostVO.setPost_rate(post_rate);
            PostVO.setPost_status(post_status);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("PostVO", PostVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/post/addPost.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            PostService PostService = new PostService();
            PostVO = PostService.addPost(post_memno, post_res_no, post_text, post_img, post_time, 
                    post_rate, post_status);
System.out.println(296);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            System.out.println(298);
            String url = "/front-end/post/post.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
            

            /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/post/addPost.jsp");
                failureView.forward(req, res);
            }
        }

        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 ***************************************/
                String post_no = new String(req.getParameter("post_no"));

                /*************************** 2.開始刪除資料 ***************************************/
                PostService PostService = new PostService();
                PostService.deletePost(post_no);

                /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
                String url = "/front-end/post/post.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/post/post.jsp");
                failureView.forward(req, res);
            }
        }

    }

}