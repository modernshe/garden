package useage.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

public class SendHttpApacheWay {
    /**
     * 使用feature接收结果，在get处阻塞，仍然是等待结果并返回
     * @throws URISyntaxException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void asyncFeatureGetWay() throws URISyntaxException, ExecutionException, InterruptedException, IOException {
        String fileUrl = "http://github.com/modernshe/garden/blob/main/resorces/cloud.jpg?raw=true";

        URIBuilder uriBuilder = new URIBuilder(fileUrl);
        URI uri = uriBuilder.build();

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().build();
        httpAsyncClient.start();
        HttpUriRequest request = RequestBuilder.get(uri).build();
        Future<HttpResponse> execute = httpAsyncClient.execute(request, null);
        HttpResponse response = execute.get(); // 阻塞

        long contentLength = response.getEntity().getContentLength();
        System.out.println("contentLength:" + contentLength);
        httpAsyncClient.close();
    }

    /**
     * 使用callback方式，当有结果后自动调用callback线程
     * @throws URISyntaxException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void asyncCallbackWay() throws URISyntaxException, ExecutionException, InterruptedException, IOException {
        String fileUrl = "http://github.com/modernshe/garden/blob/main/resorces/cloud.jpg?raw=true";

        URIBuilder uriBuilder = new URIBuilder(fileUrl);
        URI uri = uriBuilder.build();

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().build();
        httpAsyncClient.start();
        HttpUriRequest request = RequestBuilder.get(uri).build();
        CountDownLatch latch = new CountDownLatch(1);  // 设置线程等待计数器
        httpAsyncClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                long contentLength = httpResponse.getEntity().getContentLength();
                System.out.println("contentLength:" + contentLength);
                latch.countDown();
            }

            @Override
            public void failed(Exception e) {
                System.out.println("failed request");
                latch.countDown();
            }

            @Override
            public void cancelled() {
                System.out.println("cancelled request");
                latch.countDown();
            }
        });
        latch.await(); // 等待计数完成
        httpAsyncClient.close();
    }

    /**
     *  同步发送请求
     * @throws URISyntaxException
     */
    public static void synchronizedWay() throws URISyntaxException {
        String fileUrl = "http://github.com/modernshe/garden/blob/main/resorces/cloud.jpg?raw=true";

        URIBuilder uriBuilder = new URIBuilder(fileUrl);
//        uriBuilder.setPath("/get");
//        uriBuilder.addParameter("kry", "as");
        URI uri = uriBuilder.build();

        try (CloseableHttpClient httpClient = HttpClients.custom().build()) {
            HttpUriRequest request = RequestBuilder.get(uri).build();
            HttpResponse response = httpClient.execute(request);
            // get contentLength
            long contentLength = response.getEntity().getContentLength();
            System.out.println("contentLength:" + contentLength);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        asyncCallbackWay();
    }
}
