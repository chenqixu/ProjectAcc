package com.cqx.acc.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import com.cqx.acc.util.CommonUtils;

public class CallWebService {
    public static int MAX_REC_BUFFER = 20480;
    
    public URL testWebServer(String sUrl, int iTimeout){
        URL url;
        try{
	        url = new URL(sUrl);
	        String ipAddr = url.getHost();
	        int iPort = url.getPort();
	        if(iTimeout > 0){
	            Socket socket = new Socket();
	            InetSocketAddress address = new InetSocketAddress(ipAddr, iPort);
	            socket.connect(address, iTimeout);
	            socket.close();
	        }
	        return url;
        }catch(Exception ex){
        	CommonUtils.error(ex.getMessage());
	        return null;
        }
    }    

    public String doAction(String sUrl, byte aRequestContent[]){
    	String result = "";
        OutputStream out = null;
        InputStream ins = null;
        URL url = null;
        URLConnection uc = null;
        HttpURLConnection urlcon = null;
        byte aResult[] = null;
        ByteArrayOutputStream os = null;
        try{
        	url = this.testWebServer(sUrl, 0);
        	uc = url.openConnection();
        	if(uc instanceof HttpURLConnection){
        		urlcon = (HttpURLConnection)uc;
                urlcon.setRequestMethod("POST");
                if(aRequestContent != null && aRequestContent.length > 0){
                	urlcon.setDoOutput(true);
                    out = urlcon.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.write(aRequestContent);
                    dos.flush();
                    dos.close();
                    
                    out = null;
                    ins = urlcon.getInputStream();
                    aResult = null;
                    os = null;
                    os = new ByteArrayOutputStream();
                    int d = 0;
                    byte bytes[] = new byte[MAX_REC_BUFFER];
                    while((d = ins.read(bytes)) != -1) 
                        os.write(bytes, 0, d);
                    aResult = os.toByteArray();
                }
        	}
        }catch(Exception e){
        	CommonUtils.error(e.getMessage());
        }finally{
        	try{
	        	if(os != null)
	        		os.close();
	        	if(out != null)
	                out.close();
	        	if(ins != null)
	                ins.close();
        	}catch(Exception ex){
        		CommonUtils.error(ex.getMessage());
        	}
        }
        try{
        	result = new String(aResult);
        }catch(Exception e){
        	CommonUtils.error(e.getMessage());
        }
    	return result;
    }

}
