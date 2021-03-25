package com.fooditem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feastinfo.model.FeastInfoService;
import com.feastinfo.model.FeastInfoVO;
import com.fooditem.model.FooditemService;
import com.fooditem.model.FooditemVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.myfeast.model.MyFeastService;
import com.myfeast.model.MyFeastVO;
import com.post.model.PostService;
import com.main.ImageUtil;

/**
 * Servlet implementation class FoodItemList
 */
@WebServlet("/FoodItemListServlet")
public class FoodItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 取得使用者餐團清單
	protected List<FooditemVO> findResNo(String fea_resno) {
		FooditemService fooditemSrv = new FooditemService();
		List<FooditemVO> fooditemListAll = fooditemSrv.getByResNOFooditem(fea_resno);
		if (fooditemListAll != null) {
			
			return fooditemListAll;
		}else {
			System.out.println("null");
			return null;}
	}

    public FoodItemListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		System.out.println(11111);
		
		FooditemService fooditemSrv=new FooditemService();
		PostService postSrv=new PostService();
		//將請求內容存入字串
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		//將字串轉成json或gson格式，然後用key取值
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);//這個不知能否更快
		// 【取得使用者 帳號(account) 密碼(password)】
		String fea_resno = jsonObject.get("fea_resno").getAsString();
String req_type = jsonObject.get("req_type").getAsString();

		// 【飯局編號取出餐廳編號】
		List<FooditemVO> fooditemListAll =new ArrayList<FooditemVO>(); 
		
		fooditemListAll=findResNo(fea_resno);//不確定要不要先new耶
		
		
		if(("food").equals(req_type)) {
			PrintWriter out = res.getWriter();
			if (fooditemListAll == null) { // 【餐廳沒有尚未提供餐點時】
				System.out.println("nofooditem");
				out.print("nofooditem");
				
			} else { // 【餐點狀態上架中】
				//將物件轉成json再用字串格式印出
				
				List<FooditemVO> fooditemList=new ArrayList<FooditemVO>();
				System.out.println("hrhe");
				for(FooditemVO fooditem : fooditemListAll) {
					if(fooditem.getFo_status().equals("fo1")) {
					
					fooditemList.add(fooditemSrv.getOneFooditem(fooditem.getFo_no()));
//					System.out.println(fooditem.getFo_name());
	
					}
				}
				
				System.out.println("12123");
				System.out.println(gson.toJson(fooditemList));
				
				out.print(gson.toJson(fooditemList));
			}
		
	}else if(("image").equals(req_type)) {
		
		OutputStream os = res.getOutputStream();
		String fo_no = jsonObject.get("fo_no").getAsString();
		int imageSize = jsonObject.get("imageSize").getAsInt();
		byte[] image = fooditemSrv.getImgByFoNOFooditem(fo_no);
		if (image != null) {
			// 縮圖 in server side
			System.out.println("圖片");
			image = ImageUtil.shrink(image, imageSize);
			res.setContentType("image/jpeg");
			res.setContentLength(image.length);
			os.write(image);
		}
		
		
		
//		FooditemService fooditemSrv=new FooditemService();
//		List<FooditemVO> fooditemList=new ArrayList<FooditemVO>();
//		List<byte[]> imgList=new ArrayList<byte[]>();
//		for(FooditemVO fooditem : fooditemList) {
//			if(fooditem.getFo_status().equals("fo1")) {
//		
//		imgList.add(fooditemSrv.getImgByFoNOFooditem(fooditem.getFo_no()));
//			}
//		}
//		System.out.println("imagesend");
//		System.out.println(gson.toJson(imgList));
//		out.print(gson.toJson(imgList));
	}else if(("post").equals(req_type)) {//PostVO
		PrintWriter out = res.getWriter();
		System.out.println(gson.toJson(postSrv.getAll()));
		out.print(gson.toJson(postSrv.getAll()));
		
		
	}else if(("post_image").equals(req_type)) {//PostImage
		OutputStream os = res.getOutputStream();
		String post_no = jsonObject.get("post_no").getAsString();
		int imageSize = jsonObject.get("imageSize").getAsInt();
		byte[] image = postSrv.getImagePost(post_no);
		if (image != null) {
			// 縮圖 in server side
			System.out.println("圖片");
			image = ImageUtil.shrink(image, imageSize);
			res.setContentType("image/jpeg");
			res.setContentLength(image.length);
			os.write(image);
		}
		
	}

}
}
