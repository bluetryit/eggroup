package com.feastinfo.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.feastinfo.model.*;

@WebServlet("/FeastInfoServlet")
public class FeastInfoServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("getOne_For_Display".equals(action))
        { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            request.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
                String str = request.getParameter("fea_no");
                if (str == null || (str.trim()).length() == 0)
                {
                    errorMsgs.add("請輸入飯局編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher failureView = request
                            .getRequestDispatcher("/front-end/select_page.jsp");
                    failureView.forward(request, response);
                    return;// 程式中斷
                }

                String fea_no = null;
                try
                {
                    fea_no = new String(str);
                }
                catch (Exception e)
                {
                    errorMsgs.add("飯局編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher failureView = request
                            .getRequestDispatcher("/front-end/select_page.jsp");
                    failureView.forward(request, response);
                    return;// 程式中斷
                }

                /*************************** 2.開始查詢資料 *****************************************/
                FeastInfoService feastInfoSvc = new FeastInfoService();
                FeastInfoVO feastInfoVO = feastInfoSvc.getOneFeastInfo(fea_no);
                if (feastInfoVO == null)
                {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    RequestDispatcher failureView = request
                            .getRequestDispatcher("/front-end/select_page.jsp");
                    failureView.forward(request, response);
                    return;// 程式中斷
                }

                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
                request.setAttribute("feastInfoVO", feastInfoVO); // 資料庫取出的feastInfoVO物件,存入req
                String url = "/front-end/listOneFeast.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                successView.forward(request, response);

                /*************************** 其他可能的錯誤處理 *************************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/select_page.jsp");
                failureView.forward(request, response);
            }
        }

        if ("getOne_For_Update".equals(action))
        { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            request.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 ****************************************/
                String fea_no = new String(request.getParameter("fea_no"));

                /*************************** 2.開始查詢資料 ****************************************/
                FeastInfoService feastInfoSvc = new FeastInfoService();
                FeastInfoVO feastInfoVO = feastInfoSvc.getOneFeastInfo(fea_no);

                /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
                request.setAttribute("feastInfoVO", feastInfoVO); // 資料庫取出的feastInfoVO物件,存入req
                String url = "/front-end/update_feast_input.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
                successView.forward(request, response);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/listAllEmp.jsp");
                failureView.forward(request, response);
            }
        }

        if ("update".equals(action))
        { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            request.setAttribute("errorMsgs", errorMsgs);
//
//            try
//            {
                System.out.println("");
                String fea_no = new String(request.getParameter("fea_no").trim());
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                String fea_memNo = request.getParameter("fea_memNo");
                String fea_memNoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{8,8}$";
                if (fea_memNo == null || fea_memNo.trim().length() == 0)
                {
                    errorMsgs.add("主揪姓名: 請勿空白");
                }
                else if (!fea_memNo.trim().matches(fea_memNoReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("主揪姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
                }

                String fea_resNoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
                String fea_resNo = request.getParameter("fea_resNo").trim();
                if (fea_resNo == null || fea_resNo.trim().length() == 0)
                {
                    errorMsgs.add("餐廳名稱請勿空白");
                }
                else if (!fea_resNo.trim().matches(fea_resNoReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
                }

                String fea_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
                String fea_title = request.getParameter("fea_title").trim();
                if (fea_title == null || fea_title.trim().length() == 0)
                {
                    errorMsgs.add("標題名稱請勿空白");
                }
                else if (!fea_title.trim().matches(fea_titleReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("標題: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
                }

                String fea_textReg = "^[(\\u4e00-\\u9fa5)(\u3000-\u300F)(a-zA-Z0-9_)]{2,100}$";
                String fea_text = request.getParameter("fea_text").trim();
                if (fea_text == null || fea_text.trim().length() == 0)
                {
                    errorMsgs.add("飯局簡介請勿空白");
                }
                else if (!fea_text.trim().matches(fea_textReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("飯局簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
                }

                Integer fea_number = new Integer(request.getParameter("fea_number").trim());

                Integer fea_upLim = null;
                try
                {
                    fea_upLim = new Integer(request.getParameter("fea_upLim").trim());
                }
                catch (NumberFormatException e)
                {
                    fea_upLim = 0;
                    errorMsgs.add("人數上限請填數字.");
                }

                Integer fea_lowLim = null;
                try
                {
                    fea_lowLim = new Integer(request.getParameter("fea_lowLim").trim());
                }
                catch (NumberFormatException e)
                {
                    fea_lowLim = 0;
                    errorMsgs.add("人數下限請填數字.");
                }

                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                
                java.sql.Timestamp fea_date = null;
                try
                {
                    fea_date = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_date").trim()).getTime());
                }
                catch (IllegalArgumentException | ParseException e)
                {
                    fea_date = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入飯局日期!");
                }

                
                java.sql.Timestamp fea_startDate = null;
                try
                {
                    fea_startDate = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_startDate").trim()).getTime());
                }
                catch (IllegalArgumentException | ParseException e)
                {
                    fea_startDate = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入報名截止日期!");
                }

                java.sql.Timestamp fea_endDate = null;
                try
                {
                    fea_endDate = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_endDate").trim()).getTime());
                }
                catch (IllegalArgumentException | ParseException e)
                {
                    fea_endDate = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入訂餐截止日期!");
                }

                String fea_typeReg = "^[(\u4e00-\u9fa5)]{2}$";
                String fea_type = request.getParameter("fea_type").trim();
                if (fea_type == null || fea_type.trim().length() == 0)
                {
                    errorMsgs.add("飯局類型請勿空白");
                }
                else if (!fea_type.trim().matches(fea_typeReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("飯局類型: 只能是外帶,外送或內用, 且長度必需在2到100之間");
                }

                String fea_locReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)(-_)]{10,30}$";
                String fea_loc = request.getParameter("fea_loc").trim();
                if (fea_loc == null || fea_loc.trim().length() == 0)
                {
                    errorMsgs.add("聚會地址請勿空白");
                }
                else if (!fea_loc.trim().matches(fea_locReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("聚會地址: 只能是中、英文字母、數字和_ , 且長度必需在10到30之間");
                }
                
                String fea_status = request.getParameter("fea_status").trim();

                FeastInfoVO feastInfoVO = new FeastInfoVO();

                feastInfoVO.setFea_no(fea_no);
                feastInfoVO.setFea_resNo(fea_resNo);
                feastInfoVO.setFea_memNo(fea_memNo);
                feastInfoVO.setFea_title(fea_title);
                feastInfoVO.setFea_text(fea_text);
                feastInfoVO.setFea_number(fea_number);
                feastInfoVO.setFea_upLim(fea_upLim);
                feastInfoVO.setFea_lowLim(fea_lowLim);
                feastInfoVO.setFea_date(fea_date);
                feastInfoVO.setFea_startDate(fea_startDate);
                feastInfoVO.setFea_endDate(fea_endDate);
                feastInfoVO.setFea_type(fea_type);
                feastInfoVO.setFea_loc(fea_loc);
                feastInfoVO.setFea_status(fea_status);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    request.setAttribute("feastInfoVO", feastInfoVO); // 含有輸入格式錯誤的feastInfoVO物件,也存入req
                    RequestDispatcher failureView = request
                            .getRequestDispatcher("/front-end/update_feast_input.jsp");
                    failureView.forward(request, response);
                    return; // 程式中斷
                }

                /*************************** 2.開始修改資料 *****************************************/
                FeastInfoService feastInfoSvc = new FeastInfoService();
                feastInfoVO = feastInfoSvc.updateFeastInfo(fea_no, fea_resNo, fea_memNo, fea_title, fea_text,
                        fea_number, fea_upLim, fea_lowLim, fea_date, fea_startDate, fea_endDate, fea_type, fea_loc,
                        fea_status);

                /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
                request.setAttribute("feastInfoVO", feastInfoVO); // 資料庫update成功後,正確的的feastInfoVO物件,存入req
                String url = "/front-end/listOneFeast.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
                successView.forward(request, response);

                /*************************** 其他可能的錯誤處理 *************************************/
//            }
//            catch (Exception e)
//            {
//                errorMsgs.add("修改資料失敗:" + e.getMessage());
//                RequestDispatcher failureView = request
//                        .getRequestDispatcher("/front-end/update_feast_input.jsp");
//                failureView.forward(request, response);
//            }
        }

        if ("insert".equals(action))
        { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            request.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                String fea_memNo = request.getParameter("fea_memNo");
                String fea_memNoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{8,8}$";
                if (fea_memNo == null || fea_memNo.trim().length() == 0)
                {
                    errorMsgs.add("主揪姓名: 請勿空白");
                }
                else if (!fea_memNo.trim().matches(fea_memNoReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("主揪姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
                }

                String fea_resNoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,60}$";
                String fea_resNo = request.getParameter("fea_resNo").trim();
                if (fea_resNo == null || fea_resNo.trim().length() == 0)
                {
                    errorMsgs.add("餐廳名稱請勿空白");
                }
                else if (!fea_resNo.trim().matches(fea_resNoReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到60之間");
                }

                String fea_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
                String fea_title = request.getParameter("fea_title").trim();
                if (fea_title == null || fea_title.trim().length() == 0)
                {
                    errorMsgs.add("標題名稱請勿空白");
                }
                else if (!fea_title.trim().matches(fea_titleReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("標題: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
                }

                String fea_text = request.getParameter("fea_text").trim();

                Integer fea_number = 1;

                Integer fea_upLim = null;
                try
                {
                    fea_upLim = new Integer(request.getParameter("fea_upLim").trim());
                }
                catch (NumberFormatException e)
                {
                    fea_upLim = 0;
                    errorMsgs.add("人數上限請填數字.");
                }

                Integer fea_lowLim = null;
                try
                {
                    fea_lowLim = new Integer(request.getParameter("fea_lowLim").trim());
                }
                catch (NumberFormatException e)
                {
                    fea_lowLim = 0;
                    errorMsgs.add("人數下限請填數字.");
                }

                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                
                java.sql.Timestamp fea_date = null;
                try
                {
                    fea_date = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_date").trim()).getTime());
                }
                catch (IllegalArgumentException e)
                {
                    fea_date = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入飯局日期!");
                }

                
                java.sql.Timestamp fea_startDate = null;
                try
                {
                    fea_startDate = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_startDate").trim()).getTime());
                }
                catch (IllegalArgumentException e)
                {
                    fea_startDate = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入報名截止日期!");
                }

                java.sql.Timestamp fea_endDate = null;
                try
                {
                    fea_endDate = new java.sql.Timestamp(dateFormat.parse(request.getParameter("fea_endDate").trim()).getTime());
                }
                catch (IllegalArgumentException e)
                {
                    fea_endDate = new java.sql.Timestamp(System.currentTimeMillis());
                    errorMsgs.add("請輸入訂餐截止日期!");
                }

                String fea_typeReg = "^[(\u4e00-\u9fa5)]{2}$";
                String fea_type = request.getParameter("fea_type").trim();
                if (fea_type == null || fea_type.trim().length() == 0)
                {
                    errorMsgs.add("飯局類型請勿空白");
                }
                else if (!fea_type.trim().matches(fea_typeReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("飯局類型: 只能是外帶,外送或內用, 且長度必需在2到100之間");
                }

                String fea_locReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,30}$";
                String fea_loc = request.getParameter("fea_loc").trim();
                if (fea_loc == null || fea_loc.trim().length() == 0)
                {
                    errorMsgs.add("聚會地址請勿空白");
                }
                else if (!fea_loc.trim().matches(fea_locReg))
                { // 以下練習正則(規)表示式(regular-expression)
                    errorMsgs.add("聚會地址: 只能是中、英文字母、數字和_ , 且長度必需在10到30之間");
                }
                
                String fea_status = "fea1";

                FeastInfoVO feastInfoVO = new FeastInfoVO();

                feastInfoVO.setFea_resNo(fea_resNo);
                feastInfoVO.setFea_memNo(fea_memNo);
                feastInfoVO.setFea_title(fea_title);
                feastInfoVO.setFea_text(fea_text);
                feastInfoVO.setFea_number(fea_number);
                feastInfoVO.setFea_upLim(fea_upLim);
                feastInfoVO.setFea_lowLim(fea_lowLim);
                feastInfoVO.setFea_date(fea_date);
                feastInfoVO.setFea_startDate(fea_startDate);
                feastInfoVO.setFea_endDate(fea_endDate);
                feastInfoVO.setFea_type(fea_type);
                feastInfoVO.setFea_loc(fea_loc);
                feastInfoVO.setFea_status(fea_status);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty())
                {
                    request.setAttribute("feastInfoVO", feastInfoVO); // 含有輸入格式錯誤的feastInfoVO物件,也存入req
                    RequestDispatcher failureView = request
                            .getRequestDispatcher("/front-end/addFeast.jsp");
                    failureView.forward(request, response);
                    return;
                }

                /*************************** 2.開始新增資料 ***************************************/
                FeastInfoService feastInfoSvc = new FeastInfoService();
                feastInfoVO = feastInfoSvc.addFeastInfo(fea_resNo, fea_memNo, fea_title, fea_text, fea_number,
                        fea_upLim, fea_lowLim, fea_date, fea_startDate, fea_endDate, fea_type, fea_loc, fea_status);

                /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
                String url = "/front-end/listAllFeast.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                successView.forward(request, response);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/addFeast.jsp");
                failureView.forward(request, response);
            }
        }

        if ("delete".equals(action))
        { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            request.setAttribute("errorMsgs", errorMsgs);

            try
            {
                /*************************** 1.接收請求參數 ***************************************/
                String fea_no = new String(request.getParameter("fea_no"));

                /*************************** 2.開始刪除資料 ***************************************/
                FeastInfoService feastInfoSvc = new FeastInfoService();
                feastInfoSvc.deleteFeastInfo(fea_no);;

                /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
                String url = "/front-end/listAllFeast.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
                successView.forward(request, response);

                /*************************** 其他可能的錯誤處理 **********************************/
            }
            catch (Exception e)
            {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/listAllFeast.jsp");
                failureView.forward(request, response);
            }
        }
    }

}
