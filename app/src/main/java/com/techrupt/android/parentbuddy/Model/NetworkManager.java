package com.techrupt.android.parentbuddy.Model;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public abstract class NetworkManager<T> {

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;

    public abstract List<T> parseItems(JSONObject jsonobject) throws JSONException;

    public abstract String buildurlString(String operatioName);

    public NetworkManager(String baseurl)
    {
        this.baseUrl=baseurl;
    }

    public List<T> GetListofItems(String url,String requestMethod,String parameters) throws IOException,JSONException
    {
       String urlstring=buildurlString(url);
        String jsonString=getUrlString(urlstring,requestMethod,parameters);
        JSONObject jsonBody=new JSONObject(jsonString);
        List<T> items=parseItems(jsonBody);

        return items;

    }

    public byte[] getUrlBytes(String urlSpec,String requestMethod,String parameters) throws IOException
    {
        URL url=new URL(urlSpec);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();

        try
        {
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            DataOutputStream outputStream =new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(parameters);
            outputStream.flush();
            outputStream.close();

            InputStream in=connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new IOException(connection.getResponseMessage() + ": with" + urlSpec);

            int bytesRead=0;
            byte[] buffer=new byte[1024];

            while ((bytesRead=in.read(buffer))>0)
            {
                out.write(buffer,0,bytesRead);
            }
            out.close();

            return out.toByteArray();
        }
        finally {
            connection.disconnect();
        }

    }

    public String getUrlString(String urlSpec,String requestMethod,String parameters) throws  IOException
    {
        return new String(getUrlBytes(urlSpec,requestMethod,parameters));
    }

}
