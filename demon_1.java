package date;

import json.JSONArray;
import json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;



public class demon_1 {
    public  String result(String com,String nu){
        
        String requestUrl=" http://route.showapi.com/64-19";
        
        Map params=new HashMap();
        
        params.put("showapi_appid","71723");
        
        params.put("com",com);
        params.put("nu",nu);
        
        params.put("showapi_sign","b505cc562b3b4834a9b5b45273b40362");

        
        String string =httpRequest(requestUrl,params);
       
        JSONObject pageBean = new JSONObject(string);
        //pageBean.getJSONObject("showapi_res_body");
        return pageBean.toString();
    }

    private  String httpRequest(String requestUrl,Map params){
       
        StringBuffer buffer=new StringBuffer();
        try {

           
            URL url=new URL(requestUrl+"?"+urlencode(params));
            System.out.println(url);
            HttpURLConnection httpUrlConn =(HttpURLConnection)url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            
            InputStream inputStream=httpUrlConn.getInputStream();
            
            InputStreamReader inputStreamReader =new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReander=new BufferedReader(inputStreamReader);

            
            String str=null;
            while((str=bufferedReander.readLine())!=null){
                buffer.append(str);
            }

            bufferedReander.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream=null;

            httpUrlConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String urlencode(Map<String ,Object>date){
        
        StringBuffer sb=new StringBuffer();
        for (Map.Entry i:date.entrySet()){
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }


    public static void main(String[] args) {
        demon_1 d=new demon_1();
        //System.out.println(d.result("Auto","A001772341034"));
    	JSONObject jo=new JSONObject(d.result("Auto","A001772341034"));
    	//System.out.println(jo.get("showapi_res_body"));
    	
    	JSONObject jo1=new JSONObject(jo.get("showapi_res_body").toString());
    	//System.out.println(jo1.get("data"));
    	
    	JSONArray ja=new JSONArray(jo1.get("data").toString());
    	JSONObject o;
    	for(int i=0;i<ja.length();i++){
    		o=new JSONObject(ja.get(i).toString());
    		System.out.println(o.get("time"));
    		System.out.println(o.get("context"));
    		System.out.println();
    		System.out.println();
    	}
    }
}
