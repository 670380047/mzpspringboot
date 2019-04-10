package util.json;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/27 9:11
 * @File : TestJson
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class TestJson {
    public static void main(String args[]){
        try {
            jsonRequest();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通客户端测试（服务器之间的通信可使用该方式）
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void jsonRequest() throws URISyntaxException, IOException {
        //请求的地址
        String url = "http://localhost:8080/mzpWebMVC/mzp/mv1";
        //String url = "http://localhost:8080/mzpWebMVC/mzp/handle1";
        //1.创建HttpRequest(内部使用HttpURLConnection)
        ClientHttpRequest request = new SimpleClientHttpRequestFactory()
                .createRequest(new URI(url), HttpMethod.POST);
        //2.设置客户端可接受的媒体类型（即需要什么类型的响应数据） accept和控制层的produce匹配
        request.getHeaders().set("Accept","application/json");
        //Content-Type和控制层的consumes匹配
        request.getHeaders().set("Content-Type","text/html");
        //3.发送请求并得到响应
        ClientHttpResponse response = request.execute();
        //4.得到响应体的编码方式
        Charset charset = response.getHeaders().getContentType().getCharset();
        //5.得到响应体的内容
        InputStream inputStream = response.getBody();
        //response.getHeaders()  可以得到响应头，从而可以得到响应体的内容类型和编码、内容长度
        byte[] bytes = new byte[(int) response.getHeaders().getContentLength()];
        inputStream.read(bytes);
        String jsonData = new String(bytes,charset);
        System.out.println("charset:"+charset+"  json data:"+jsonData);
    }
}
