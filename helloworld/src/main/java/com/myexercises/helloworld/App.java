package com.myexercises.helloworld;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class App {
	public static void main(String[] args) throws IOException, HttpException {
 		App restAction = new App();
 		restAction.getRequest();
 	}
 
 	public void getRequest() throws HttpException, IOException {
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(new HttpHost("dev63479.service-now.com")),
                new UsernamePasswordCredentials("admin", "Chanquadi@123"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        try {
            HttpGet httpget = new HttpGet("https://dev63479.service-now.com/api/now/table/incident");
            httpget.setHeader("Accept", "application/json");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
 	}

}