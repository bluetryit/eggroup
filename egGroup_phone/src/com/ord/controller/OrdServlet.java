package com.ord.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdByFeastVO;
import com.ord.model.OrdFooditemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ord_details.model.Ord_detailsVO;
import com.feastinfo.model.*;
import com.fooditem.model.FooditemService;

/**
 * Servlet implementation class OrdServlet
 */
@WebServlet("/OrdServlet")
public class OrdServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdServlet()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        res.getWriter().append("Served at: ").append(req.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        BufferedReader br = req.getReader();
        StringBuilder jsonIn = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null)
        {
            jsonIn.append(line);
        }
        System.out.println("input2: " + jsonIn);

        JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
        OrdService ordSrv = new OrdService();
        FooditemService fooditemSrv = new FooditemService();
        MemService memSrv = new MemService();
        String action = jsonObject.get("action").getAsString();
        String userId = jsonObject.get("userId").getAsString();

        if ("add".equals(action) && userId != null)
        {
            System.out.println("add");
            // 裡面莫名有斜線
            String temp = jsonObject.get("order").toString();
            temp = temp.replace("\\", "");
            temp = temp.substring(1, temp.length());
            temp = temp.substring(0, temp.length() - 1);
            System.out.println(temp);

            OrdVO ordVO = gson.fromJson(temp.toString(), OrdVO.class);
            Timestamp d = new Timestamp(System.currentTimeMillis());
            // 填入餐團訂餐截止時間還是當前系統時間?
            // 法2:先抓餐團類型如果是現場就抓系統時間，外帶外送抓餐團訂單截止時間

            ordVO.setOrd_date(d);

            List<Ord_detailsVO> ord_detailsVOList = new ArrayList<>();// orderItemList
            List<OrdFooditemVO> ordFooditemVOList = ordVO.getOrdFooditemVOList();
            for (OrdFooditemVO ordFooditemVO : ordFooditemVOList)
            {

                ordVO.setOrd_resno(ordFooditemVO.getFo_resno());
                Ord_detailsVO odt = new Ord_detailsVO();
                odt.setDet_fono(ordFooditemVO.getFo_no());
                odt.setDet_price(ordFooditemVO.getFo_price());
                odt.setDet_quantity(ordFooditemVO.getQuantity());

                ord_detailsVOList.add(odt);
            }
            OrdVO ord = null;// ordMaster
            System.out.println("取得ordVO餐廳編號" + ordVO.getOrd_resno());
            String ord_no = ordSrv.insertWithDetailsOrd(ordVO, ord_detailsVOList);
            if (!ord_no.equals(-1))
            {
                System.out.println("!ord_no.equals(-1),成立");
                ord = ordSrv.getOneOrd(ord_no);
                ord.setOrdFooditemVOList(ordFooditemVOList);
            }

            System.out.println(gson.toJson(ord));
            out.print(gson.toJson(ord));

        }
        else if ("memGetAll".equals(action) && userId != null)
        {
            // 用mem_no取出其所有訂單，和其ord_details將ord_details的List塞到List<ordfooditem>將一整個ordVO傳回去
            // 然後依狀態分為his跟now
            List<OrdVO> ordVOList = ordSrv.getByMem(userId);// 為甚麼這個不用new阿
            List<OrdVO> his_full_ordVOList = new ArrayList();// 填入ord_details的
            List<OrdVO> now_full_ordVOList = new ArrayList();// 填入ord_details的

            for (OrdVO ordVO : ordVOList)
            {
                // 歷史訂單
                if (ordVO.getOrd_status() == "ords5" || ordVO.getOrd_status() == "ords6")
                {// 取得訂單狀態，判斷是歷史訂單還是目前訂單
                    List<Ord_detailsVO> ord_detailsVOList = new ArrayList(ordSrv.getOrdDetByOrdno(ordVO.getOrd_no()));// 取出ordDetailList
                    List<OrdFooditemVO> ordFooditemVOList = new ArrayList();
                    for (Ord_detailsVO ord_detailsVO : ord_detailsVOList)
                    {// 轉成OrdFooditemVO
                        OrdFooditemVO ordFooditemVO = new OrdFooditemVO(
                                fooditemSrv.getOneFooditem(ord_detailsVO.getDet_fono()),
                                ord_detailsVO.getDet_quantity());
                        ordFooditemVOList.add(ordFooditemVO);
                    }
                    ordVO.setOrdFooditemVOList(ordFooditemVOList);
                    his_full_ordVOList.add(ordVO);
                }
                else
                {// 目前訂單
                    List<Ord_detailsVO> ord_detailsVOList = new ArrayList(ordSrv.getOrdDetByOrdno(ordVO.getOrd_no()));// 取出ordDetailList
                    List<OrdFooditemVO> ordFooditemVOList = new ArrayList();
                    for (Ord_detailsVO ord_detailsVO : ord_detailsVOList)
                    {// 轉成OrdFooditemVO
                        OrdFooditemVO ordFooditemVO = new OrdFooditemVO(
                                fooditemSrv.getOneFooditem(ord_detailsVO.getDet_fono()),
                                ord_detailsVO.getDet_quantity());
                        ordFooditemVOList.add(ordFooditemVO);
                    }
                    ordVO.setOrdFooditemVOList(ordFooditemVOList);
                    now_full_ordVOList.add(ordVO);
                }
            }
            JsonObject jsonObjectResult = new JsonObject();
            jsonObjectResult.addProperty("his", gson.toJson(his_full_ordVOList));
            jsonObjectResult.addProperty("now", gson.toJson(now_full_ordVOList));

            System.out.println(jsonObjectResult);
            out.print(jsonObjectResult);

        }
        else if ("memFindOne".equals(action) && userId != null)
        {
            // 確認會員是否已經完成訂購，避免重複訂購
            String ord_fea_no = jsonObject.get("fea_no").getAsString();
            OrdVO ordVO = ordSrv.findByMemFeast(userId, ord_fea_no);
            if (ordVO == null)
            {
                System.out.println("memFindOne return null");
                out.print("null");
            }
            else
            {
                out.print(gson.toJson(ordVO));
            }

        }
        else if ("ResFindAll".equals(action) && userId != null)
        {// 這裡的userid是餐廳編號
            // 取出該餐廳所有訂單
            FeastInfoService feastSrv = new FeastInfoService();
            List<OrdByFeastVO> ordByFeastVOList = new ArrayList();// 集大成!!!!!!
         
            List<OrdVO> ordVOList = ordSrv.getByRes(userId);// 所有該餐廳的訂單
            //
            // 先篩選為今日訂單 或訂單狀態為未完成的 (未定)
            //
            HashSet<String> feastSet = new HashSet();// 找出該餐廳所有餐團的訂單
            for (OrdVO ordVO : ordVOList)
            {
//				if(!ordVO.getOrd_status().equalsIgnoreCase("ords6")){//檢查該訂單狀態是否為之前的歷史訂單
                feastSet.add(ordVO.getOrd_fea_no());// set篩選掉重複的值
//				}
            }
            // 檢查set有沒成功濾掉，順便依時間排序
            for (String str : feastSet)
            {
                System.out.println("set檢查:" + str);
            }

            for (String ord_fea_no : feastSet)
            {
                OrdByFeastVO ordByFeastVO = new OrdByFeastVO();// 自定義一個VO存資料 ，這放在回全里不知道會不會比較節省資源
                System.out.println("ord_fea_no = " + ord_fea_no);
                if (ordSrv.CountFeastOrd(ord_fea_no) >= feastSrv.getOneFeastInfo(ord_fea_no).getFea_number())
                {// 當訂單筆數=餐團人數
                    int total = 0;
                    List<OrdVO> feast_ordVOList = ordSrv.getByFeast(ord_fea_no);// 飯局所有訂單
                    List<OrdVO> full_feast_ordVOList = new ArrayList();// 存已經放入 OrdFooditemVOList的 ordVOList

                    for (OrdVO feast_ordVO : feast_ordVOList)
                    {
                        // 取出每個訂單的訂單明細
                        List<Ord_detailsVO> feast_ord_detailsVOList = new ArrayList(
                                ordSrv.getOrdDetByOrdno(feast_ordVO.getOrd_no()));// 直接將set轉成arrayList
                        List<OrdFooditemVO> ordFooditemVOList = new ArrayList();// 存放轉成OrdFooditemVO的訂單明細
                        for (Ord_detailsVO feast_ord_detailsVO : feast_ord_detailsVOList)
                        {// 轉成OrdFooditemVO 方便放進ordVO
                            OrdFooditemVO ordFooditemVO = new OrdFooditemVO();
                            ordFooditemVO.setFo_no(feast_ord_detailsVO.getDet_fono());
                            ordFooditemVO.setQuantity(feast_ord_detailsVO.getDet_quantity());
                            ordFooditemVO.setFo_price(feast_ord_detailsVO.getDet_price());
                            ordFooditemVO.setFo_name(fooditemSrv.getOneFooditem(feast_ord_detailsVO.getDet_fono()).getFo_name());
//							fooditemSrv.getOneFooditem(feast_ord_detailsVO.getDet_fono()),feast_ord_details/VO.getDet_quantity()
                            System.out.println("這裡面它嗎怎麼了" + gson.toJson(ordFooditemVO));
                            ordFooditemVOList.add(ordFooditemVO);
                        }

                        // 將ordFooditemVOList放進ordVO
                        feast_ordVO.setOrdFooditemVOList(ordFooditemVOList);
                        total += feast_ordVO.getOrd_price();// 加總每筆訂單的費用

                        full_feast_ordVOList.add(feast_ordVO);
                    }
                    System.out.println("-----------------------");
                    
                    
                    
                    // 將所有放入自定義VO
                    ordByFeastVO.setOrdVO(full_feast_ordVOList);
                    FeastInfoVO feastInfoVO = feastSrv.getOneFeastInfo(((OrdVO) (ordByFeastVO.getOrdVO().get(0))).getOrd_fea_no());
                    ordByFeastVO.setFeastinfoVO(feastInfoVO);

                    ordByFeastVO.setMem_name(memSrv.memFindByPrimaryKey(feastInfoVO.getFea_memNo()).getMem_name());
                    ordByFeastVO.setMem_phone(memSrv.memFindByPrimaryKey(feastInfoVO.getFea_memNo()).getMem_ph());

                    ordByFeastVO.setTotal(total);
                }
                // 將自定義的VO(一個飯局一個物件) 填入list
                ordByFeastVOList.add(ordByFeastVO);
            }

            // 最後將ordByFeastVOList轉成json物件
            System.out.println("!!!!!!!終極結果!!!!!!!");
            if (ordByFeastVOList.get(0).getOrdVO() != null)
            {
                System.out.println(gson.toJson(ordByFeastVOList));
                out.print(gson.toJson(ordByFeastVOList));
            }
            else
            {
                out.print("null");
            }
        }

//		else if ("getAll".equals(action) && userId != null) {//歷史訂單
//			String start = jsonObject.get("start").getAsString();
//			String end = jsonObject.get("end").getAsString();
//			List<OrdVO> ordVOList = ordSrv.getAll();//getAll(userId, start, end);
//			if (orderList != null && !orderList.isEmpty()) {
//				// JOIN Book data from OrderItem
//				OrderItemDAO itemDao = new OrderItemDAOImpl();
//				BookDAO bookDao = new BookDAOImpl();
//				List<OrderBook> orderBookList = null;
//				for (OrderMaster order : orderList) {
//					int orderId = order.getOrderId();
//					orderBookList = new ArrayList<>();
//					List<OrderItem> itemList = itemDao.findByOrderId(orderId);
//					for (OrderItem item : itemList) {
//						Book book = bookDao.findByISBN(item.getIsbn());
//						OrderBook orderBook = new OrderBook(book, item.getQuantity());
//						orderBookList.add(orderBook);
//					}
//					order.setOrderBookList(orderBookList);
//				}
//				writeText(res, gson.toJson(orderList));
//			}
//		}
    }

}
