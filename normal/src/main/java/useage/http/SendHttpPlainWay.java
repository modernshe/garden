package useage.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SendHttpPlainWay {
    public static void main(String[] args) throws IOException {
        String fileUrl = "http://localhost:8080/get1";
        URLConnection connection = new URL(fileUrl).openConnection();
        connection.setConnectTimeout(3000);

        // get contentLength
        long contentLength = connection.getContentLengthLong();
        System.out.println("contentLength:" + contentLength);

        // save to desk
        Files.copy(connection.getInputStream(), Paths.get("test.mp3"), StandardCopyOption.REPLACE_EXISTING);
    }
}
