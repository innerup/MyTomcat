package httpresolver;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 用来在返回response时添加响应头信息
 */
public class HttpResponse {

    public  static void response404(OutputStream output) throws IOException {
        StringBuilder response=new StringBuilder();
        response.append("HTTP/1.1 404 File Not Found\r\n");
        response.append("Content-Type: text/html\r\n");
        response.append("Content-Length: 22\r\n");
        response.append("\r\n");
        response.append("<h1>404 Not Found</h1>");
        output.write(response.toString().getBytes());
    }

}
