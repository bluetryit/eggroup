package com.res.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.res.model.*;
import com.tools.*;

@WebServlet("/res.do")
@MultipartConfig()
public class ResServlet extends HttpServlet
{

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        System.out.println("action" + action);
        if ("getOneRes_For_Display".equals(action))
        {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to send the ErrorPage
            // view.

            req.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
                String str = req.getParameter("res_no");
                if (str == null || (str.trim()).length() == 0)
                    errorMsgs.add("請輸入餐廳編號");

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher faillureView = req
                            .getRequestDispatcher("/back-end/res/select_page.jsp");
                    faillureView.forward(req, res);
                    return; // 程式中斷
                }

                String res_no = null;
                try
                {
                    res_no = new String(str);
                }
                catch (Exception e)
                {
                    errorMsgs.add("餐廳編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/back-end/res/select_page.jsp");
                    failureView.forward(req, res);
                    return; // 程式中斷
                }

                /*************************** 2.開始查詢資料 *****************************************/
                ResService resSvc = new ResService();
                ResVO resVO = resSvc.getOneRes(res_no);// findByPK
                if (resVO == null)
                    errorMsgs.add("查無資料");

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/back-end/res/select_page.jsp");
                    failureView.forward(req, res);
                    return; // 程式中斷
                }

                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
                req.setAttribute("resVO", resVO); // 資料庫取出的resVO物件,存入req
                String url = "/front-end/res/listOneRes.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/back-end/res/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOneRes_For_Update".equals(action))
        { // 來自listAllRes.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to send the ErrorPage
            // view.

            req.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 ****************************************/
                String res_no = req.getParameter("res_no").trim();

                /*************************** 2.開始查詢資料 ****************************************/
                ResService resSvc = new ResService();
                ResVO resVO = resSvc.getOneRes(res_no);

                /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
                req.setAttribute("resVO", resVO); // 資料庫取出的resVO物件,存入req
                String url = "/front-end/res/update_res_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_emp_input.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher FailureView = req
                        .getRequestDispatcher("/back-end/res/listAllRes.jsp");
                FailureView.forward(req, res);
            }
        }

        if ("update".equals(action))
        {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to send the ErrorPage
            // view.

            req.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
                String res_no = req.getParameter("res_no").trim();
                ResService resSvc = new ResService();
                session = req.getSession();
                ResVO resVO = (ResVO) session.getAttribute("resVO");
//				ResVO resVO = resSvc.getOneRes(res_no);
                String res_name = req.getParameter("res_name");
                String res_nameReg = "^[(\u2E80-\u9FFF)(a-zA-Z0-9)]{2,50}$";
                if (res_name == null || res_name.trim().length() == 0)
                {
                    errorMsgs.add("餐廳名稱:請勿空白");
                }
                else if (!res_name.trim().matches(res_nameReg))
                {
                    errorMsgs.add("餐廳名稱:只能是中、日、韓、英文、數字且數字大於兩個字");
                }

                String res_adrs = req.getParameter("res_adrs");
                String res_adrsReg = "^[(\u4e00-\u9fa5)(0-9)(\\-)]{2,100}$";
                if (res_adrs == null || res_adrs.trim().length() == 0)
                {
                    errorMsgs.add("餐廳地址:請勿空白");
                }
                else if (!res_adrs.trim().matches(res_adrsReg))
                {
                    errorMsgs.add("餐廳地址:請輸入中文地址");
                }

                String res_ph = req.getParameter("res_ph");
                if (res_ph == null || res_ph.trim().length() == 0)
                {
                    errorMsgs.add("電話號碼請勿空白");
                }

                Integer res_point = new Integer(req.getParameter("res_point"));
                String res_ac = req.getParameter("res_ac");

                String res_pass = req.getParameter("res_pass");
                String res_pass2 = req.getParameter("res_pass2");
                String res_passReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,10}$";
                if (res_pass == null || res_pass.trim().length() == 0)
                {
                    errorMsgs.add("請設定密碼");
                }
                else if (!res_pass.trim().matches(res_passReg))
                {
                    errorMsgs.add("請輸入正確的密碼格式，必須包含至少一個英文大小寫及一個數字，至少8碼至多10碼");
                }
                else if (!res_pass.trim().equals(res_pass2.trim()))
                {
                    errorMsgs.add("密碼與密碼確認須一致");
                }

                Part part = req.getPart("res_img");
                InputStream in = part.getInputStream();
                byte[] res_img = null;
                if (in.available() == 0)
                {
                    res_img = resVO.getRes_img();
                }
                else
                {
                    in = part.getInputStream();
                    res_img = new byte[in.available()];
                    in.read(res_img);
                }
                in.close();

                String res_intro = req.getParameter("res_intro");
                if (res_intro == null || res_intro.trim().length() == 0)
                {
                    errorMsgs.add("請輸入店家資訊");
                }

                String res_start = req.getParameter("res_start");
                if (res_start == null || res_start.trim().length() == 0)
                {
                    errorMsgs.add("請輸入營業開始時間");
                }

                String res_end = req.getParameter("res_end");

                if (res_end == null || res_end.trim().length() == 0)
                {
                    errorMsgs.add("請輸入營業結束時間");
                }
                if (res_end.equals(res_start))
                {
                    errorMsgs.add("營業開始時間不能等於結束時間");
                }
                Double res_lat = new Double(req.getParameter("res_lat"));
                Double res_lot = new Double(req.getParameter("res_lot"));

                HashMap<String, Double> latLot = GMaps.Gmap(res_adrs);
                if (latLot != null)
                {
                    res_lot = latLot.get("lot");
                    res_lat = latLot.get("lat");
                }

                Integer res_score = new Integer(req.getParameter("res_score"));

                Integer res_comcount = new Integer(req.getParameter("res_comcount"));

                Integer res_cost = new Integer(req.getParameter("res_cost"));
                if (res_cost == null || res_cost.toString().trim().length() == 0)
                {
                    errorMsgs.add("請輸入消費水準");
                }

                String res_type = req.getParameter("res_type");

                if (res_type == null || res_type.trim().length() == 0)
                {
                    errorMsgs.add("請填寫餐廳類型");
                }

                String res_status = req.getParameter("res_status");

                resVO.setRes_no(res_no);
                resVO.setRes_adrs(res_adrs);
                resVO.setRes_name(res_name);
                resVO.setRes_ph(res_ph);
                resVO.setRes_point(res_point);
                resVO.setRes_ac(res_ac);
                resVO.setRes_pass(res_pass);

                resVO.setRes_img(res_img);

                resVO.setRes_intro(res_intro);
                resVO.setRes_start(res_start);
                resVO.setRes_end(res_end);
                resVO.setRes_lat(res_lat);
                resVO.setRes_lot(res_lot);
                resVO.setRes_score(res_score);
                resVO.setRes_cost(res_cost);
                resVO.setRes_comcount(res_comcount);
                resVO.setRes_type(res_type);
                resVO.setRes_status(res_status);

                // Send the use back to the form, if there were errors

                if (!errorMsgs.isEmpty())
                {
                    req.setAttribute("resVO", resVO); // 含有輸入格式錯誤的empVO物件,也存入req
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/front-end/res/update_res_input.jsp");
                    failureView.forward(req, res);
                    return; // 程式中斷
                }

                /*************************** 2.開始修改資料 *****************************************/

                resVO = resSvc.updateRes(res_no, res_adrs, res_name, res_ph, res_point, res_ac, res_pass, res_img,
                        res_intro, res_start, res_end, res_lat, res_lot, res_score, res_cost, res_comcount, res_type,
                        res_status);

                /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
                req.setAttribute("resVO", resVO); // 資料庫update成功後,正確的的resVO物件,存入req
                String url = "/front-end/res/listOneRes.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRes.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/res/update_res_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action))
        { // 來自addRes.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to send the ErrorPage
            // view.

            req.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                String res_name = req.getParameter("res_name");
                String res_nameReg = "^[(\u2E80-\u9FFF)(a-zA-Z0-9)]{2,50}$";
                if (res_name == null || res_name.trim().length() == 0)
                {
                    errorMsgs.add("餐廳名稱:請勿空白");
                }
                else if (!res_name.trim().matches(res_nameReg))
                {
                    errorMsgs.add("餐廳名稱:只能是中、日、韓、英文、數字且數字大於兩個字");
                }
                String res_city = req.getParameter("res_city");
                if (res_city == null || res_city.trim().length() == 0)
                {
                    errorMsgs.add("請選擇縣市");
                }
                String res_town = req.getParameter("res_town");
                if (res_town == null || res_town.trim().length() == 0)
                {
                    errorMsgs.add("請選擇鄉鎮市區");
                }
                String address = req.getParameter("address");
                String res_adrs = res_city + res_town + address;
                String res_adrsReg = "^[(\u4e00-\u9fa5)(0-9)(\\-)]{2,100}$";
                if (address == null || address.trim().length() == 0)
                {
                    errorMsgs.add("餐廳地址:請勿空白");
                }
                else if (!res_adrs.trim().matches(res_adrsReg))
                {
                    errorMsgs.add("餐廳地址:請輸入中文地址");
                }

                String res_ph = req.getParameter("res_ph");
                if (res_ph == null || res_ph.trim().length() == 0)
                {
                    errorMsgs.add("電話號碼請勿空白");
                }

                Integer res_point = 0; // 店家點數初始預設值
                String res_ac = req.getParameter("res_ac");

                String res_pass = req.getParameter("res_pass");
                String res_pass2 = req.getParameter("res_pass2");
                String res_passReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,10}$";
                if (res_pass == null || res_pass.trim().length() == 0)
                {
                    errorMsgs.add("請設定密碼");
                }
                else if (!res_pass.trim().matches(res_passReg))
                {
                    errorMsgs.add("請輸入正確的密碼格式，必須包含至少一個英文大小寫及一個數字，至少8碼至多10碼");
                }
                else if (!res_pass.trim().equals(res_pass2.trim()))
                {
                    errorMsgs.add("密碼與密碼確認須一致");
                }

                Part part = req.getPart("res_img");
                InputStream in = part.getInputStream();
                byte[] res_img = null;
                if (in.available() == 0)
                {
                    errorMsgs.add("請上傳圖片");
                }
                else
                {
                    in = part.getInputStream();
                    res_img = new byte[in.available()];
                    in.read(res_img);
                }
                in.close();

                String res_intro = req.getParameter("res_intro");
                if (res_intro == null || res_intro.trim().length() == 0)
                {
                    errorMsgs.add("請輸入店家資訊");
                }

                String res_start = req.getParameter("res_start");
                if (res_start == null || res_start.trim().length() == 0)
                {
                    errorMsgs.add("請輸入營業開始時間");
                }

                String res_end = req.getParameter("res_end");

                if (res_end == null || res_end.trim().length() == 0)
                {
                    errorMsgs.add("請輸入營業結束時間");
                }
                if (res_end.equals(res_start))
                {
                    errorMsgs.add("營業開始時間不能等於結束時間");
                }
                Double res_lot = 0.0;
                Double res_lat = 0.0;
                if (res_city.trim().length() != 0 && res_town.trim().length() != 0 && address.trim().length() != 0)
                {

                    HashMap<String, Double> latLot = GMaps.Gmap(res_adrs);
                    if (latLot != null)
                    {
                        res_lot = latLot.get("lot");
                        res_lat = latLot.get("lat");
                    }
                }

                Integer res_score = 0; // 新增餐廳預設0

                Integer res_comcount = 0; // 新增餐廳預設0

                Integer res_cost = new Integer(req.getParameter("res_cost"));

                if (res_cost == null || res_cost.toString().trim().length() == 0)
                {
                    errorMsgs.add("請輸入消費水準");
                }

                String res_type = req.getParameter("res_type");

                if (res_type == null || res_type.trim().length() == 0)
                {
                    errorMsgs.add("請填寫餐廳類型");
                }

                String res_status = "res1"; // 餐廳狀態預設res1

                ResVO resVO = new ResVO();

                resVO.setRes_adrs(res_adrs);
                resVO.setRes_name(res_name);
                resVO.setRes_ph(res_ph);
                resVO.setRes_point(res_point);
                resVO.setRes_ac(res_ac);
                resVO.setRes_pass(res_pass);
                resVO.setRes_img(res_img);
                resVO.setRes_intro(res_intro);
                resVO.setRes_start(res_start);
                resVO.setRes_end(res_end);
                resVO.setRes_lat(res_lat);
                resVO.setRes_lot(res_lot);
                resVO.setRes_score(res_score);
                resVO.setRes_cost(res_cost);
                resVO.setRes_comcount(res_comcount);
                resVO.setRes_type(res_type);
                resVO.setRes_status(res_status);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    req.setAttribute("resVO", resVO);
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/front-end/res/addRes.jsp");
                    failureView.forward(req, res);
                    return;
                }

                /*************************** 2.開始新增資料 ***************************************/
                ResService resSvc = new ResService();
                resVO = resSvc.addRes(res_adrs, res_name, res_ph, res_point, res_ac, res_pass, res_img, res_intro,
                        res_start, res_end, res_lat, res_lot, res_score, res_cost, res_comcount, res_type, res_status);

                /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

                session.setAttribute("resReg", "true");
                String url = "/hometag.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/res/addRes.jsp");
                failureView.forward(req, res);
            }
        }
        if ("showResInfo".equals(action))
        {
            String res_no = req.getParameter("res_no");

            ResService resSvc = new ResService();
            ResVO resVO = resSvc.getOneRes(res_no);

            req.setAttribute("resVO", resVO);
            String url = "/front-end/res/showResInfo.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }
        if ("review".equals(action))
        {

            String res_no = req.getParameter("res_no");
            String res_status = req.getParameter("res_status");

            if (res_status.equals("res2"))
            {
                ResService resSvc = new ResService();
                ResVO resVO = resSvc.getOneRes(res_no);

                String res_name = resVO.getRes_name();
                String res_adrs = resVO.getRes_adrs();
                String res_ph = resVO.getRes_ph();
                Integer res_point = resVO.getRes_point();
                String res_ac = resVO.getRes_ac();
                String res_pass = resVO.getRes_pass();
                byte[] res_img = resVO.getRes_img();
                String res_intro = resVO.getRes_intro();
                String res_start = resVO.getRes_start();
                String res_end = resVO.getRes_end();
                Double res_lot = resVO.getRes_lot();
                Double res_lat = resVO.getRes_lat();
                Integer res_score = resVO.getRes_score();
                Integer res_comcount = resVO.getRes_comcount();
                Integer res_cost = resVO.getRes_cost();
                String res_type = resVO.getRes_type();

                resVO.setRes_no(res_no);
                resVO.setRes_adrs(res_adrs);
                resVO.setRes_name(res_name);
                resVO.setRes_ph(res_ph);
                resVO.setRes_point(res_point);
                resVO.setRes_ac(res_ac);
                resVO.setRes_pass(res_pass);
                resVO.setRes_img(res_img);
                resVO.setRes_intro(res_intro);
                resVO.setRes_start(res_start);
                resVO.setRes_end(res_end);
                resVO.setRes_lat(res_lat);
                resVO.setRes_lot(res_lot);
                resVO.setRes_score(res_score);
                resVO.setRes_cost(res_cost);
                resVO.setRes_comcount(res_comcount);
                resVO.setRes_type(res_type);
                resVO.setRes_status(res_status);

                resVO = resSvc.updateRes(res_no, res_adrs, res_name, res_ph, res_point, res_ac, res_pass, res_img,
                        res_intro, res_start, res_end, res_lat, res_lot, res_score, res_cost, res_comcount, res_type,
                        res_status);

                Send send = new Send();
                String[] tel = {res_ph};
                String message = "親愛的" + res_name + "您好！\r\n" +
                        "您的餐廳資料已審核完畢，請您盡早新增菜單以便後續餐廳上架作業。\r\n" +
                        "謝謝您！";
                send.sendMessage(tel, message);

                req.setAttribute("resVO", resVO);
                session.setAttribute("successReviewRes", "true");
                String url = "/back-end/res/reviewRes.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
            }
            else
            {
                String url = "/back-end/res/reviewRes.jsp";
                session.setAttribute("noChoice", "true");
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
                return;
            }

        }

        if ("reviewFooditem".equals(action))
        {
            String res_no = req.getParameter("res_no").trim();

            ResService resSvc = new ResService();
            ResVO resVO = resSvc.getOneRes(res_no);

            req.setAttribute("resVO", resVO);
            String url = "/back-end/fooditem/reviewFooditem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }

        if ("delete".equals(action))
        { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to send the ErrorPage
            // view.

            req.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 ***************************************/

                String res_no = req.getParameter("res_no");

                /*************************** 2.開始刪除資料 ***************************************/
                ResService resSvc = new ResService();
                resSvc.deleteRes(res_no);
                /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/

                String url = "/back-end/res/listAllRes.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher faillureView = req
                        .getRequestDispatcher("/back-end/res/listAllRes.jsp");
                faillureView.forward(req, res);
            }
        }
    }
}
