package com.tools;

//30貼上你的金鑰
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetResterauntFromGoogleForTeam
{
    static String driver = "oracle.jdbc.driver.OracleDriver";
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static String userid = "DA101G6";
    static String passwd = "123456";

    final static String INSERT_STMT = "Insert into RESTAURANT (RES_NO, RES_ADRS, RES_NAME, RES_PH, RES_POINT, "
            + "RES_AC, RES_PASS, RES_IMG, RES_INTRO, RES_START, "
            + "RES_END, RES_LAT, RES_LOT, RES_SCORE, RES_COST, "
            + "RES_COMCOUNT, RES_TYPE, RES_STATUS) "
            + "values ('RS'||LPAD(to_char(res_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 貼上你的金鑰
    static String yourKey = "AIzaSyC5NIlL7UvXlQD34fr-UZ2klG17qQMfOZM";
    static String NEXT_PAGE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
            + "&language=zh-TW&key=" + yourKey + "&pagetoken=";

    static int successCounter = 0;
    static int failCounter = 0;

    static Connection con = null;
    static PreparedStatement pstmt = null;
    static Integer accountCount = 499;

    public static void printInformation(String MY_URL) throws IOException, ClassNotFoundException, InterruptedException
    {
        Class.forName(driver);
        try
        {
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        URL url = new URL(MY_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setUseCaches(false);
        InputStream is = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str;
        while ((str = br.readLine()) != null)
            sb.append(str);

        br.close();
        isr.close();
        is.close();
        con.disconnect();

        System.out.println("===================================");

        JSONObject jObj = new JSONObject(sb.toString());
        
        if (jObj.has("results"))
        {
            JSONArray results = jObj.getJSONArray("results");
            Integer crawlerCount = 0;
            for (int i = 0; i < results.length(); i++)
            {
                try
                {
                    crawlerCount++;
                    if (crawlerCount == 10)
                    {
                        System.out.println("end");
                        break;
                    }
                    successCounter++;
                    JSONObject data = results.getJSONObject(i);

                    if (data.has("vicinity"))
                    {
                        String vicinity = data.getString("vicinity");

                        pstmt.setString(1, vicinity);
                        System.out.println("餐廳的地址: " + vicinity);
                    }

                    if (data.has("name"))
                    {
                        String name = data.getString("name");

                        pstmt.setString(2, name);
                        System.out.println("餐廳名稱 : " + name);
                    }

                    String phoneNum = "03-1234567";
                    pstmt.setString(3, phoneNum);
                    System.out.println("電話:" + phoneNum);

                    int point = (int) (Math.random() * 100000);
                    pstmt.setInt(4, point);
                    System.out.println("點數" + point);

                    accountCount++;
                    String account = "AC" + (int) (accountCount + 100000);
                    pstmt.setString(5, account);
                    System.out.println("帳號:" + account);

                    String pass = "Aa123456";
                    pstmt.setString(6, pass);
                    System.out.println("密碼" + pass);

                    if (data.has("photos"))
                    {
                        JSONArray photoArray = data.getJSONArray("photos");
                        JSONObject photo = photoArray.getJSONObject(0);
                        int height = photo.getInt("height");
                        int width = photo.getInt("width");
                        String photo_reference = photo.getString("photo_reference");

                        byte[] pic = getPicture(height, width, photo_reference);

                        pstmt.setBytes(7, pic);
                        System.out.println(pic);
                    }
                    else
                    {
                        byte[] pic = null;
                        pstmt.setBytes(7, pic);
                    }

                    String intro = "你好，我們餐廳很好吃。";
                    pstmt.setString(8, intro);

                    String[] minuteArr = {"00", "30"};
                    String startHour = (int) Math.floor((Math.random() * 4 + 7)) + ":"
                            + minuteArr[(int) Math.round(Math.random())];

                    String endHours = (int) Math.floor((Math.random() * 7 + 15)) + ":"
                            + minuteArr[(int) Math.round(Math.random())];

                    pstmt.setString(9, startHour);
                    System.out.println("營業開始時間:" + startHour);

                    pstmt.setString(10, endHours);
                    System.out.println("營業結束時間:" + endHours);

                    if (data.has("geometry"))
                    {
                        JSONObject geometry = data.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        Double lat = location.getDouble("lat");
                        Double lng = location.getDouble("lng");

                        pstmt.setDouble(11, lat);
                        System.out.println("餐廳的緯度: " + lat);

                        pstmt.setDouble(12, lng);
                        System.out.println("餐廳的經度: " + lng);
                    }
                    if (data.has("user_ratings_total"))
                    {
                        int user_ratings_total = data.getInt("user_ratings_total");

                        pstmt.setInt(15, user_ratings_total);
                        System.out.println("餐廳評分人數" + user_ratings_total);

                        if (data.has("rating"))
                        {
                            Double rating = data.getDouble("rating");

                            pstmt.setInt(13, (int) (rating * user_ratings_total));
                            System.out.println("餐廳的總分: " + (int) (rating * user_ratings_total));

                        }
                    }
                    else
                    {
                        int user_ratings_total = (int) Math.random() * 1000;

                        pstmt.setInt(15, user_ratings_total);
                        System.out.println("餐廳評分人數" + user_ratings_total);

                        if (data.has("rating"))
                        {
                            Double rating = (double) Math.round(Math.random() * 50) / 10.f;

                            pstmt.setDouble(13, (int) (rating * user_ratings_total));
                            System.out.println("餐廳的總分: " + (int) (rating * user_ratings_total));

                        }
                    }
                    int priceLevel = (int) Math.floor(Math.random() * 5);
                    if (data.has("price_level"))
                    {
                        priceLevel = data.getInt("price_level");
                    }
                    pstmt.setInt(14, priceLevel);
                    System.out.println("消費區間:" + priceLevel);

//                if (data.has("opening_hours"))
//                {
//                    JSONObject opening_hours = data.getJSONObject("opening_hours");
//                    boolean open_now = opening_hours.getBoolean("open_now");
//
//                    if (open_now)
//                    {
//                        System.out.println("餐廳是否營業中:是");
//                    }
//                    else
//                    {
//                        System.out.println("餐廳是否營業中:否");
//                    }
//                }

//                    if (data.has("types"))
//                    {
//                        JSONArray typeArr = data.getJSONArray("types");
//                        String type = typeArr.getString(0);
//
//                        pstmt.setString(16, type);
//                        System.out.println("餐廳類型" + type);
//                    }
//                    else
//                    {
                        String type = "restaurant";

                        pstmt.setString(16, type);
                        System.out.println("餐廳類型" + type);
//                    }


                    pstmt.setString(17, "res3");
                    System.out.println("狀態" + "res3");

                    System.out.println("============================");
                    pstmt.executeUpdate();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
//                  System.err.println("JSON出錯了");
                    successCounter--;
                    failCounter++;
                    continue;
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
//                  System.err.println("SQL新增餐廳出錯了");
                    successCounter--;
                    failCounter++;
                    continue;
                }
            }
        }
        Thread.sleep(1000);

            

    }

    public static byte[] getPicture(int height, int width, String photo_reference) throws IOException
    {
        String photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxheight=" + height + "&maxwidth=" + width
                + "&photoreference=" + photo_reference + "&key=" + yourKey;

        URL url = new URL(photoUrl);
        InputStream is = url.openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        byte[] byteChunk = new byte[4096];
        
        int n;

        while ( (n = is.read(byteChunk)) > 0 ) 
        {
          baos.write(byteChunk, 0, n);
        }
        
        
        byte[] pic = baos.toByteArray();

        return pic;
    }

    public static void main(String[] args)
    {

        try
        {
            for (int i = 0; i < 88; i++)
            {
                String[] latLotArr = 
                {
                        
//                    桃園 22

                    "24.9579269,121.2641266",
                    "24.965397,121.2840393",
                    "24.9481,121.2565984",
                    "24.9568157,121.2241544",
                    "24.9644581,121.0645269",
                    "24.9630132,121.0462037",
                    "24.9318787,121.1874281",
                    "25.0304568,121.2553419",
                    "24.9729245,121.1795483",
                    "24.9226188,121.1327936",
                    
                    "24.9204305,121.1246429",
                    "24.9553126,121.2408517",
                    "24.9591649,121.2145252",
                    "24.9951981,121.310467",
                    "25.0169256,121.2967061",
                    "24.9564551,121.2456562",
                    "24.9635751,121.2309362",
                    "24.9447081,121.2119921",
                    "24.9853542,121.3209385",
                    "25.005815,121.3079192",
                    "25.0258977,121.2984685",
                    "24.9894399,121.263435",
                    

//                    新北 10
                    "24.9799732,121.3883506",
                    "25.0320376,121.3895412",
                    "25.068641,121.3829945",
                    "25.0638067,121.3630769",
                    "25.0709121,121.4023238",
                    "25.0556561,121.4082815",
                    "25.0120109,121.4465461",
                    "25.0047301,121.4815258",
                    "25.0379385,121.4630309",
                    "25.069298,121.5120827",

//                    台北 8
                    "25.0033636,121.4147056",
                    "25.0093304,121.5567185",
                    "24.9971606,121.5470067",
                    "25.0131214,121.3890555",
                    "25.0501498,121.5020629",
                    "25.0545357,121.5200368",
                    "25.0537079,121.5371448",
                    "25.0390062,121.5539839",

//                    基隆 3
                    "25.1113204,121.706441",
                    "25.1071799,121.6694031",
                    "25.0662643,121.8317119",

//                    宜蘭 4
                    "24.7672623,121.7361424",
                    "24.7033105,121.733694",
                    "24.6790739,121.7677637",
                    "24.8502647,121.801626",

//                    花蓮 5
                    "23.9413843,121.5850648",
                    "23.8635771,121.5544781",
                    "23.5945168,121.4506924",
                    "23.1247471,121.2816536",
                    "23.1457742,121.2843099",

//                    台東 2
                    "22.7471572,121.0604818",
                    "22.705259,121.0328177",

//                    屏東 3
                    "21.9987479,120.7345119",
                    "22.0780874,120.7195166",
                    "22.4070781,120.5669658",

//                    高雄 5
                    "22.6280973,120.2966369",
                    "22.7421762,120.263549",
                    "22.8081139,120.2248089",
                    "22.8668624,120.2312987",
                    "22.6188955,120.3024494",

//                    台南 7
                    "22.9494519,120.1159541",
                    "22.9923318,120.1652",
                    "23.0186311,120.1705759",
                    "23.1515602,120.1480631",
                    "23.2305572,120.1812222",
                    "23.23328,120.1672536",
                    "22.9915034,120.2010151",

//                    嘉義 3
                    "23.3352192,120.1863571",
                    "23.4054318,120.1864955",
                    "23.5095412,120.2585914",

//                    雲林 2
                    "23.7002989,120.2868866",
                    "23.727464,120.2174012",

//                    彰化 2
                    "24.033189,120.4385294",
                    "24.0692171,120.4687319",

//                    台中 5
                    "24.2063246,120.5574947",
                    "24.2369867,120.5601084",
                    "24.2041797,120.4864096",
                    "24.1687447,120.5782078",
                    "24.1483582,120.6695738",

//                    苗栗 2
                    "24.4241246,120.5600259",
                    "24.4241246,120.5600259",

//                    新竹 5
                    "24.6969785,120.8264574",
                    "24.7587878,120.8762731",
                    "24.7881787,120.9596966",
                    "24.8291233,120.9853098",
                    "24.8525653,120.9690495",
                };

                    String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                            + "location=" + latLotArr[i] + "&radius=5000&types=" + "restaurant" + "&language=zh-TW&key=" + yourKey;

                    printInformation(GOOGLE_URL);

            }
            System.out.println("成功新增" + successCounter + "個資料");
            System.out.println("新增" + failCounter + "個資料失敗");

        }
        catch (ClassNotFoundException | IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}
