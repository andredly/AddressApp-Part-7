package ch.makery.address.util;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class NetUtil {

    public void get() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://helpx.adobe.com/experience-manager/using/first-arch10.html");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
            response1.getAllHeaders();
        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            try {
                response1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public NetUtil() throws IOException {
    }

    public static void main(String[] args) throws IOException, AuthenticationException {
        new NetUtil().get();

//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://www.example.com");
//
//        String json = "{\"id\":ff,\"name\":\"John\"}";
//        StringEntity entity = new StringEntity(json);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//        CloseableHttpResponse response = client.execute(httpPost);
//        HttpEntity entity1 = response.getEntity();
//        String theString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//        response.getStatusLine().getStatusCode();
//        client.close();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        httpPost.setEntity(new StringEntity("test post"));
        UsernamePasswordCredentials creds
                = new UsernamePasswordCredentials("John", "pass");
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));

        CloseableHttpResponse response = client.execute(httpPost);
        response.getStatusLine().getStatusCode();
        client.close();

//        http://www.baeldung.com/httpclient-post-http-request
//        https://docs.postman-echo.com/
//        https://superuser.com/questions/149329/what-is-the-curl-command-line-syntax-to-do-a-post-request
    }
}
