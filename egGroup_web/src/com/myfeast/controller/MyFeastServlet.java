package com.myfeast.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feastinfo.model.FeastInfoService;
import com.feastinfo.model.FeastInfoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemVO;
import com.myfeast.model.MyFeastService;

@WebServlet("/MyFeastController")
public class MyFeastServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("結果有進來嗎安卓: ");
        HttpSession session = request.getSession();
        String mye_feaNo = null;
        MemVO memVO = null;

        System.out.println("request.getRequestURI()" + request.getRequestURI());

        if (request.getRequestURI().toString().endsWith("MyFeastController"))
        {// 安卓
            System.out.println("android");
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            BufferedReader br = request.getReader();

            StringBuilder jsonIn = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
            {
                System.out.println("55" + line);
                jsonIn.append(line);
            }
            System.out.println("input: " + jsonIn);
            // 將字串轉成json或gson格式，然後用key取值
            JsonObject reqjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
            JsonObject resjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
            // 【取得使用者編號】
            String action = reqjsonObject.get("action").getAsString();
            String mem_no = reqjsonObject.get("mem_no").getAsString();
            String fea_no = reqjsonObject.get("fea_no").getAsString();

            joinfeast(request, response, mem_no, fea_no, "android");
            // Store this set in the request scope, in case we need to

        }
        else if (request.getRequestURI().toString().endsWith(".do"))
        {

            String action = request.getParameter("action");

            if ("joinfeast".equals(action))
            {
                mye_feaNo = (String) request.getParameter("mye_feano");
                memVO = (MemVO) session.getAttribute("memberVO");

                List<String> errorMsgs = joinfeast(request, response, memVO.getMem_no(), mye_feaNo, "net");
                // Store this set in the request scope, in case we need to
                // send the ErrorPage view.
                request.setAttribute("errorMsgs", errorMsgs);
            }

            if ("leftfeast".equals(action))
            {
                List<String> errorMsgs = new LinkedList<String>();
                // Store this set in the request scope, in case we need to
                // send the ErrorPage view.
                request.setAttribute("errorMsgs", errorMsgs);
                try
                {
                    mye_feaNo = (String) request.getParameter("mye_feano");
                    memVO = (MemVO) session.getAttribute("memberVO");

                    MyFeastService myFeastService = new MyFeastService();
                    myFeastService.deleteMyFeast(mye_feaNo, memVO.getMem_no());

                    FeastInfoService feastInfoService = new FeastInfoService();
                    FeastInfoVO feastInfoVO = feastInfoService.getOneFeastInfo(mye_feaNo);
                    request.setAttribute("feastInfoVO", feastInfoVO);

                    String url = "/front-end/feast/listOneFeast.jsp";
                    RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                    successView.forward(request, response);
                }
                catch (Exception e)
                {
                    errorMsgs.add(e.getMessage());
                    RequestDispatcher failureView = request.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");

                    failureView.forward(request, response);
                }
            }

            if ("kick_from_feast".equals(action))
            {
                List<String> errorMsgs = new LinkedList<String>();
                // Store this set in the request scope, in case we need to
                // send the ErrorPage view.
                request.setAttribute("errorMsgs", errorMsgs);
                try
                {
                    mye_feaNo = (String) request.getParameter("mye_feano");
                    String mye_memNo = (String) request.getParameter("mye_memno");

                    MyFeastService myFeastService = new MyFeastService();
                    myFeastService.deleteMyFeast(mye_feaNo, mye_memNo);

                    FeastInfoService feastInfoService = new FeastInfoService();
                    FeastInfoVO feastInfoVO = feastInfoService.getOneFeastInfo(mye_feaNo);
                    request.setAttribute("feastInfoVO", feastInfoVO);

                    String url = "/front-end/feast/listOneFeast.jsp";
                    RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                    successView.forward(request, response);
                }
                catch (Exception e)
                {
                    errorMsgs.add(e.getMessage());
                    RequestDispatcher failureView = request.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");

                    failureView.forward(request, response);
                }
            }

        }

    }

    protected List<String> joinfeast(HttpServletRequest request, HttpServletResponse response, String mye_memNo,
            String mye_feaNo, String type) throws ServletException, IOException
    {

        List<String> errorMsgs = new LinkedList<String>();

        try
        {

            synchronized (MyFeastServlet.class)
            {

                FeastInfoService feastInfoService = new FeastInfoService();
                FeastInfoVO feastInfoVO = feastInfoService.getOneFeastInfo(mye_feaNo);
                MyFeastService myFeastService = new MyFeastService();

                System.out.println("feastInfoVO.getFea_upLim() = " + feastInfoVO.getFea_upLim());
                System.out.println("feastInfoVO.getFea_number() = " + feastInfoVO.getFea_number());
                if (feastInfoVO.getFea_upLim() > feastInfoVO.getFea_number())
                {
                    myFeastService.addMyFeast(mye_feaNo, mye_memNo);
                    feastInfoVO = feastInfoService.getOneFeastInfo(mye_feaNo);
                    System.out.println("新增成員");
                }
                else
                {
                    errorMsgs.add("人數已滿");
                }
                request.setAttribute("feastInfoVO", feastInfoVO);

                if (!errorMsgs.isEmpty() && "net".equalsIgnoreCase(type))
                {
                    RequestDispatcher failureView = request.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");
                    failureView.forward(request, response);
                    return errorMsgs;// 程式中斷
                }
                else if (!errorMsgs.isEmpty() && "android".equalsIgnoreCase(type))
                {
                    PrintWriter out = response.getWriter();
                    out.print("full");
                    
                }
            }

            if ("net".equalsIgnoreCase(type))
            {
                String url = "/front-end/feast/listOneFeast.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                successView.forward(request, response);
            }

        }
        catch (Exception e)
        {
            errorMsgs.add(e.getMessage());
            RequestDispatcher failureView = request.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");

            failureView.forward(request, response);
        }
        return errorMsgs;// 程式中斷

    }

}
