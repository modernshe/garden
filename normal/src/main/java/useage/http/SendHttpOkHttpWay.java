package useage.http;

import okhttp3.*;

import java.io.IOException;
public class SendHttpOkHttpWay {
    /**
     * 异步发送请求
     */
    public static void asynchronousWay(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("https://github.com/modernshe/garden/blob/main/resorces/cloud.jpg?raw=true")
                .build();
        Call call = client.newCall(request);
        System.out.println(Thread.currentThread().getName());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("failed");
            }
            @Override
            public void onResponse(Call call, final Response response) {
                System.out.println(Thread.currentThread().getName());
                ResponseBody body = response.body();
                if (body != null) {
                    long contentLength = body.contentLength();
                    System.out.println("contentLength:" + contentLength);
                }
            }
        });
        client.dispatcher().executorService().shutdown(); // 关闭连接池
    }


    /**
     * 同步发送请求
     */
    public static void synchronizedWay() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("http://github.com/modernshe/garden/blob/main/resorces/cloud.jpg?raw=true")
                .build();
        Call call = client.newCall(request);

        //同步调用,返回Response,会抛出IO异常
        Response response = call.execute();
        ResponseBody body = response.body();
        if (response.isSuccessful() && body != null) {
            long contentLength = body.contentLength();
            System.out.println("contentLength:" + contentLength);
        } else {
            System.out.println("failed");
        }
        response.close();
    }

    public static void main(String[] args){
        asynchronousWay();
    }
}
