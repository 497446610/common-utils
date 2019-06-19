package cn.kuangxf.common.utils.baidu;

import com.baidu.aip.imagesearch.AipImageSearch;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class ImageUtils
{
    public static final String APP_ID = "15295056";
    public static final String API_KEY = "xUweI89tCyoxEGLQmmhIGOUF";
    public static final String SECRET_KEY = "gnUMtZrLF3mVIWF1c3KanAH1goDhUU0X";
    private static ImageUtils imageUtils = null;
    AipImageSearch client = null;
    private ImageUtils(){}
    /**
     * 相似图片入库
     * @param brief
     * @param tags
     */
    public  void similarAdd(String brief,String tags,String urlStr) {
        // 传入可选参数调用接口
        AipImageSearch aipImageSearchClient = getImageUtils().getAipImageSearchClient();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("brief", brief);
        options.put("tags", tags);
        // 相同图检索—入库, 图片参数为远程url图片
        byte[] imageFromNetByUrl = getImageFromNetByUrl(urlStr);
        JSONObject res = aipImageSearchClient.similarAdd(imageFromNetByUrl, options);
    }

    /**
     * 图片对比相识度
     * @return
     */

    public  List<Double> imageSimilarity(InputStream inputStream, String tags,List<Double> scoreList){
        AipImageSearch aipImageSearchClient = getImageUtils().getAipImageSearchClient();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("tags",tags);
        options.put("tag_logic", "0");
        //options.put("pn", "100");
        //options.put("rn", "250");
        byte[] bytes = null;
        JSONObject res = null;
        try
        {
           bytes = readInputStream(inputStream);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if(bytes!=null){
            res = aipImageSearchClient.similarSearch(bytes, options);
        }
        JSONArray resultArray = res.getJSONArray("result");
        System.out.println(res.toString(2));
        for(int i = 0;i<resultArray.length();i++){
            JSONObject jsonObject = resultArray.getJSONObject(i);
            double score = jsonObject.getDouble("score");
            BigDecimal b = new BigDecimal(score);
            score = b.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)).doubleValue();
            scoreList.add(score);
        }
        return scoreList;
    }
    public static ImageUtils getImageUtils(){
        imageUtils =imageUtils == null?new ImageUtils() : imageUtils;
        return imageUtils;
    }
    public AipImageSearch getAipImageSearchClient(){
        client = client==null?new AipImageSearch(APP_ID, API_KEY, SECRET_KEY):client;
        return client;
    }
    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static void main(String[] args){
        getImageUtils().similarAdd("{'name':'周杰伦', 'id':'666'}","100",
                "http://192.168.31.221/1077096743120982016.png");
    }
}
