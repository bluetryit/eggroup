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
            // ?????????????????????
            String temp = jsonObject.get("order").toString();
            temp = temp.replace("\\", "");
            temp = temp.substring(1, temp.length());
            temp = temp.substring(0, temp.length() - 1);
            System.out.println(temp);

            OrdVO ordVO = gson.fromJson(temp.toString(), OrdVO.class);
            Timestamp d = new Timestamp(System.currentTimeMillis());
            // ???????????????????????????????????????????????????????
            // ???2:?????????????????????????????????????????????????????????????????????????????????????????????

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
            System.out.println("??????ordVO????????????" + ordVO.getOrd_resno());
            String ord_no = ordSrv.insertWithDetailsOrd(ordVO, ord_detailsVOList);
            if (!ord_no.equals(-1))
            {
                System.out.println("!ord_no.equals(-1),??????");
                ord = ordSrv.getOneOrd(ord_no);
                ord.setOrdFooditemVOList(ordFooditemVOList);
            }

            System.out.println(gson.toJson(ord));
            out.print(gson.toJson(ord));

        }
        else if ("memGetAll".equals(action) && userId != null)
        {
            // ???mem_no??????????????????????????????ord_details???ord_details???List??????List<ordfooditem>????????????ordVO?????????
            // ?????????????????????his???now
            List<OrdVO> ordVOList = ordSrv.getByMem(userId);// ?????????????????????new???
            List<OrdVO> his_full_ordVOList = new ArrayList();// ??????ord_details???
            List<OrdVO> now_full_ordVOList = new ArrayList();// ??????ord_details???

            for (OrdVO ordVO : ordVOList)
            {
                // ????????????
                if (ordVO.getOrd_status() == "ords5" || ordVO.getOrd_status() == "ords6")
                {// ????????????????????????????????????????????????????????????
                    List<Ord_detailsVO> ord_detailsVOList = new ArrayList(ordSrv.getOrdDetByOrdno(ordVO.getOrd_no()));// ??????ordDetailList
                    List<OrdFooditemVO> ordFooditemVOList = new ArrayList();
                    for (Ord_detailsVO ord_detailsVO : ord_detailsVOList)
                    {// ??????OrdFooditemVO
                        OrdFooditemVO ordFooditemVO = new OrdFooditemVO(
                                fooditemSrv.getOneFooditem(ord_detailsVO.getDet_fono()),
                                ord_detailsVO.getDet_quantity());
                        ordFooditemVOList.add(ordFooditemVO);
                    }
                    ordVO.setOrdFooditemVOList(ordFooditemVOList);
                    his_full_ordVOList.add(ordVO);
                }
                else
                {// ????????????
                    List<Ord_detailsVO> ord_detailsVOList = new ArrayList(ordSrv.getOrdDetByOrdno(ordVO.getOrd_no()));// ??????ordDetailList
                    List<OrdFooditemVO> ordFooditemVOList = new ArrayList();
                    for (Ord_detailsVO ord_detailsVO : ord_detailsVOList)
                    {// ??????OrdFooditemVO
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
            // ?????????????????????????????????????????????????????????
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
        {// ?????????userid???????????????
            // ???????????????????????????
            FeastInfoService feastSrv = new FeastInfoService();
            List<OrdByFeastVO> ordByFeastVOList = new ArrayList();// ?????????!!!!!!
         
            List<OrdVO> ordVOList = ordSrv.getByRes(userId);// ????????????????????????
            //
            // ???????????????????????? ?????????????????????????????? (??????)
            //
            HashSet<String> feastSet = new HashSet();// ????????????????????????????????????
            for (OrdVO ordVO : ordVOList)
            {
//				if(!ordVO.getOrd_status().equalsIgnoreCase("ords6")){//???????????????????????????????????????????????????
                feastSet.add(ordVO.getOrd_fea_no());// set?????????????????????
//				}
            }
            // ??????set??????????????????????????????????????????
            for (String str : feastSet)
            {
                System.out.println("set??????:" + str);
            }

            for (String ord_fea_no : feastSet)
            {
                OrdByFeastVO ordByFeastVO = new OrdByFeastVO();// ???????????????VO????????? ?????????????????????????????????????????????????????????
                System.out.println("ord_fea_no = " + ord_fea_no);
                if (ordSrv.CountFeastOrd(ord_fea_no) >= feastSrv.getOneFeastInfo(ord_fea_no).getFea_number())
                {// ???????????????=????????????
                    int total = 0;
                    List<OrdVO> feast_ordVOList = ordSrv.getByFeast(ord_fea_no);// ??????????????????
                    List<OrdVO> full_feast_ordVOList = new ArrayList();// ??????????????? OrdFooditemVOList??? ordVOList

                    for (OrdVO feast_ordVO : feast_ordVOList)
                    {
                        // ?????????????????????????????????
                        List<Ord_detailsVO> feast_ord_detailsVOList = new ArrayList(
                                ordSrv.getOrdDetByOrdno(feast_ordVO.getOrd_no()));// ?????????set??????arrayList
                        List<OrdFooditemVO> ordFooditemVOList = new ArrayList();// ????????????OrdFooditemVO???????????????
                        for (Ord_detailsVO feast_ord_detailsVO : feast_ord_detailsVOList)
                        {// ??????OrdFooditemVO ????????????ordVO
                            OrdFooditemVO ordFooditemVO = new OrdFooditemVO();
                            ordFooditemVO.setFo_no(feast_ord_detailsVO.getDet_fono());
                            ordFooditemVO.setQuantity(feast_ord_detailsVO.getDet_quantity());
                            ordFooditemVO.setFo_price(feast_ord_detailsVO.getDet_price());
                            ordFooditemVO.setFo_name(fooditemSrv.getOneFooditem(feast_ord_detailsVO.getDet_fono()).getFo_name());
//							fooditemSrv.getOneFooditem(feast_ord_detailsVO.getDet_fono()),feast_ord_details/VO.getDet_quantity()
                            System.out.println("????????????????????????" + gson.toJson(ordFooditemVO));
                            ordFooditemVOList.add(ordFooditemVO);
                        }

                        // ???ordFooditemVOList??????ordVO
                        feast_ordVO.setOrdFooditemVOList(ordFooditemVOList);
                        total += feast_ordVO.getOrd_price();// ???????????????????????????

                        full_feast_ordVOList.add(feast_ordVO);
                    }
                    System.out.println("-----------------------");
                    
                    
                    
                    // ????????????????????????VO
                    ordByFeastVO.setOrdVO(full_feast_ordVOList);
                    FeastInfoVO feastInfoVO = feastSrv.getOneFeastInfo(((OrdVO) (ordByFeastVO.getOrdVO().get(0))).getOrd_fea_no());
                    ordByFeastVO.setFeastinfoVO(feastInfoVO);

                    ordByFeastVO.setMem_name(memSrv.memFindByPrimaryKey(feastInfoVO.getFea_memNo()).getMem_name());
                    ordByFeastVO.setMem_phone(memSrv.memFindByPrimaryKey(feastInfoVO.getFea_memNo()).getMem_ph());

                    ordByFeastVO.setTotal(total);
                }
                // ???????????????VO(????????????????????????) ??????list
                ordByFeastVOList.add(ordByFeastVO);
            }

            // ?????????ordByFeastVOList??????json??????
            System.out.println("!!!!!!!????????????!!!!!!!");
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

//		else if ("getAll".equals(action) && userId != null) {//????????????
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
