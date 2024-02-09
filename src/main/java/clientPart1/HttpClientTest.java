package clientPart1;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;

//for test
public class HttpClientTest {


    private static String url = "http://34.221.172.225:8080/assignment1/skiers/1/seasons/2019/days/1/skiers/123";
    public static void main(String[] args) {
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        PostMethod method = new PostMethod(url);
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        long acc = 0;
        try {
            for (int i = 0; i < 100; i++) {
                long startTime = System.currentTimeMillis();
                int statusCode = client.executeMethod(method);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                acc += elapsedTime;
//                if (statusCode != HttpStatus.SC_OK) {
//                    System.err.println("Method failed: " + method.getStatusLine());
//                }
//                byte[] responseBody = method.getResponseBody();
//                System.out.println(new String(responseBody));
            }
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
        System.out.println("average deal time:" + acc / 100);
    }
}