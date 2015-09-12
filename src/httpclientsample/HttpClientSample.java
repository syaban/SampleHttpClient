/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclientsample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author wrismawan
 */
public class HttpClientSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {
        /** METHOD GET EXAMPLE **/
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet("http://localhost:8000/test/api?id=100");  
        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);
        
        if (response.getStatusLine().getStatusCode() != 200){
            throw new RuntimeException("Failed : HTTP error code : " 
                    + response.getStatusLine().getStatusCode());
        }
            
        InputStreamReader isr = new InputStreamReader((response.getEntity().getContent()));
        BufferedReader br = new BufferedReader(isr);
        
        String output;
        System.out.println("Response:\n");
        
        while ((output = br.readLine()) != null){
            JSONObject jsonObj = new JSONObject(output);
            System.out.println("json id : "+jsonObj.get("id"));
        }
        
        httpClient.getConnectionManager().shutdown();
    }
}
