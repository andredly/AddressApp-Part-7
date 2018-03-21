package ch.makery.address.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.util.EntityUtils;

import java.io.File;
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
        } finally {
            try {
                response1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public NetUtil() throws IOException {
    }

    public static void installPackage(String localhost, int port, String login, String password, String filePath) throws IOException, AuthenticationException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        SystemDefaultCredentialsProvider systemDefaultCredentialsProvider = new SystemDefaultCredentialsProvider();
        HttpHost targetHost = new HttpHost(localhost, port, "http");
        systemDefaultCredentialsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort(), AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(login, password));
        httpClientBuilder.setDefaultCredentialsProvider(systemDefaultCredentialsProvider);
        HttpPost httpPost = new HttpPost(targetHost.getSchemeName() + "://" + targetHost.getHostName() + ":" + targetHost.getPort() + "/crx/packmgr/service.jsp");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipartEntityBuilder.addPart("force", new StringBody("true", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addPart("install", new StringBody("true", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addPart("file", new FileBody(new File(filePath)));
        httpPost.setEntity(multipartEntityBuilder.build());
        System.out.println("executing request " + httpPost.getRequestLine());
        HttpResponse httpResponse = httpClientBuilder.build().execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(httpResponse.getStatusLine());
        if (entity != null) {
            System.out.println(EntityUtils.toString(entity));
        }
    }
}
