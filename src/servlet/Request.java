package servlet;


import java.io.IOException;
import java.io.InputStream;

public class Request {

    private String url;
    private String method;
    private InputStream inputStream;

    public Request(InputStream inputStream) throws IOException {
        this.inputStream=inputStream;
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        //System.out.println(new String(bytes));
        this.parse(new String(bytes));

    }

    private void parse(String content){//解析请求头
        if(content.equals("")){
            System.out.println("null");
        }
        else{
            String firstLine = content.split("\\n")[0];//正则表达式匹配
            String[] split = firstLine.split("\\s");//正则表达式匹配空格
            this.setUrl(split[1]);
            this.setMethod(split[0]);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
