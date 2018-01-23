package com.ycb.util;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baolong on 2017/1/12.
 */
public class HttpUtils {

    private static Logger logger= Logger.getLogger("HttpUtils");

    /**
     * 获取HttpServletRequest对象中Attribute
     * @param request
     * @return
     */
    public static final Map<String, Object> getRequestHeaderMap(HttpServletRequest request){
        Enumeration<?> rheadernames = request.getHeaderNames();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Enumeration<?> e = rheadernames; e.hasMoreElements();) {
            String thisName = e.nextElement().toString();
            Object thisValue = request.getAttribute(thisName);
//            System.out.println("Attribute:"+thisName + "=[" + thisValue + "]");
            map.put(thisName, thisValue);
        }
        return map;
    }

    /**
     * 获取HttpServletRequest对象中Parameter
     * @param request
     * @return
     */
    public static final Map<String, Object> getRequestParameterMap(HttpServletRequest request){
        Enumeration<?> rparameternames = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Enumeration<?> e = rparameternames; e.hasMoreElements();) {
            String thisName = e.nextElement().toString();
            Object thisValue = request.getParameter(thisName);
//            System.out.println("Parameter:"+thisName + "=[" + thisValue + "]");
            map.put(thisName, thisValue);
        }
        return map;
    }

    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static final String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip != null && ip.trim().contains(",")) {
            //如果IP不为空，并且包含逗号，则说明在LVS/f5/nginx层有多层跳转，会使IP路径过长
            //此处只获取最早入口的IP
            ip = ip.trim().split(",")[0].trim();
        }
        return ip;
    }

    public static Map<String,String> DEFAULT_JSON_HEADER_UTF_8= new HashMap<String, String>();

    static{
        DEFAULT_JSON_HEADER_UTF_8.put("Content-Type", "application/json;charset=utf-8");
        //DEFAULT_JSON_HEADER_UTF_8.put("Accept", "application/json;charset=utf-8");
    }


    public static String submitPost(String url, String paramContent) {
        StringBuilder responseMessage = null;
        java.net.URLConnection connection = null;
        java.net.URL reqUrl = null;
        OutputStreamWriter reqOut = null;
        InputStream in = null;
        BufferedReader br = null;
        String param = paramContent;
        try {
            logger.debug("submitPost:url=" + url);
            logger.debug("submitPost:paramContent=" + paramContent );
            responseMessage = new StringBuilder();
            reqUrl = new java.net.URL(url);
            connection = reqUrl.openConnection();
            connection.setDoOutput(true);
            reqOut = new OutputStreamWriter(connection.getOutputStream());
            reqOut.write(param);
            reqOut.flush();
            int charCount = -1;
            in = connection.getInputStream();

            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((charCount = br.read()) != -1) {
                responseMessage.append((char) charCount);
            }
            logger.debug("submitPost:responseMessage=" + responseMessage);
        } catch (Exception ex) {
            logger.error("ERROR", ex);
        } finally {
            try {
                in.close();
                reqOut.close();
            } catch (Exception e) {
                logger.error("ERROR", e);
            }
        }
        return responseMessage.toString();
    }

    public static String submitGet(String strUrl) {
        URLConnection connection = null;
        BufferedReader reader = null;
        String str = null;
        try {
            logger.debug("submitGet:url=" + strUrl);
            URL url = new URL(strUrl);
            connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer linebuff = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                linebuff.append(lines);
            }
            str = linebuff.toString();
            logger.debug("submitGet:responseMessage=" + linebuff);
        } catch (Exception e) {
            logger.error("ERROR", e);

        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                logger.error("ERROR", e);
            }
        }
        return str;
    }

    /**
     * 提交的时候设置header,主要的header包括<br>
     * Content-Type: text/json;charset=utf-8<br>
     * Accept: text/json
     * @param url
     * @param paramContent
     * @param headers
     * @return
     */
    public static String submitPostByHeader(String url,String paramContent, Map<String, String> headers) {
        StringBuilder responseMessage = null;
        java.net.URLConnection connection = null;
        java.net.URL reqUrl = null;
        OutputStreamWriter reqOut = null;
        InputStream in = null;
        BufferedReader br = null;
        String param = paramContent;
        try {
            logger.debug("submitPostByHeader:url=" + url);
            logger.debug("submitPostByHeader:paramContent=" + paramContent );
            responseMessage = new StringBuilder();
            reqUrl = new java.net.URL(url);
            connection = reqUrl.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setDoOutput(true);
            for(Map.Entry<String, String> e :headers.entrySet()){
                connection.setRequestProperty(e.getKey(), e.getValue());
            }
            reqOut = new OutputStreamWriter(connection.getOutputStream());
            reqOut.write(param);
            reqOut.flush();
            in = connection.getInputStream();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            byte[] tmp = new byte[512];
            int count=0;
            while((count=in.read(tmp))>0){
                bao.write(tmp,0,count);
            }
            String msg = new String(bao.toByteArray(),"UTF-8");
            responseMessage.append(msg);
            logger.debug("submitPostByHeader:responseMessage=" + responseMessage);
        } catch (Exception ex) {
            logger.error("ERROR", ex);
        } finally {
            try {
                in.close();
                reqOut.close();
            } catch (Exception e) {
                logger.error("ERROR", e);
            }
        }
        return responseMessage.toString();
    }
    /**
     * 提交的时候设置header,主要的header包括<br>
     * Content-Type: text/json;charset=utf-8<br>
     * Accept: text/json
     * @param strUrl
     * @param headers
     * @return
     */
    public static String submitGetByHeader(String strUrl, Map<String, String> headers) {
        URLConnection connection = null;
        BufferedReader reader = null;
        String str = null;
        StringBuilder responseMessage = null;
        try {
            logger.debug("submitGetByHeader:url=" + strUrl);
            responseMessage = new StringBuilder();
            URL url = new URL(strUrl);
            connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            for(Map.Entry<String, String> e :headers.entrySet()){
                connection.setRequestProperty(e.getKey(), e.getValue());
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer linebuff = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                linebuff.append(lines);
            }
            str = linebuff.toString();
            responseMessage.append(str);
            logger.debug("submitGet:responseMessage=" + str);
        } catch (Exception e) {
            logger.error("ERROR", e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                logger.error("ERROR", e);
            }
        }
        return responseMessage.toString();
    }

    public static void main(String[] args) {
//        String sb = submitPostByHeader("http://localhost:8080", "{\"a\":\"aa\"}",DEFAULT_JSON_HEADER_UTF_8);
//        String  s=  submitGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx88cb890e1e079473&secret=5982d81fbb3a64d413e9a4f1eabe0898&"
//        		+ "code=001uQYww1HOwoa0gI3zw1bOgxw1uQYwM&grant_type=authorization_code");
        String  s=  submitGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx88cb890e1e079473&secret=5982d81fbb3a64d413e9a4f1eabe0898&code=071Y0cDq1gv6bn0Ep5Cq1VX2Dq1Y0cDB&grant_type=authorization_code");
        System.out.println(s.toString());
    }
}
