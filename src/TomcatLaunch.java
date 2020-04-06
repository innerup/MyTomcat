import config.ServletConfig;
import config.ServletConfigMapping;
import servlet.Request;
import servlet.Response;
import servlet.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TomcatLaunch {

    private int port = 8888;

    private Map<String,Class<Servlet>> stringClassMap = new HashMap<>();//设置map集合存放servlet对象与反射来的servlet对象

    public TomcatLaunch() {

    }

    public TomcatLaunch(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public void initServlet() throws ClassNotFoundException {
        try{
        for(ServletConfig servletConfig: ServletConfigMapping.getConfigs()){

            stringClassMap.put(servletConfig.getUrlMapping(), (Class<Servlet>) Class.forName(servletConfig.getMyclass()));
            //读取配置文件，将url参数和servlet对象的对应放入Map中
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void out(Request request,Response response) throws IllegalAccessException, InstantiationException {

        System.out.println(stringClassMap);
        Class<Servlet> servletClass = stringClassMap.get(request.getUrl());

        Servlet servlet = servletClass.newInstance();
        servlet.service(request,response);//将请求转发给servlet
    }

    public void select(Request request,Response response) throws IOException, InstantiationException, IllegalAccessException {//用来查询访问静态资源或者是访问servlet
        if(request.getUrl().equals("/")){//主界面时，读取welcome.html文件

            response.writeHtml("welcome.html");

        }else if(stringClassMap.get(request.getUrl())==null){//当url在map中找不到映射时，读取静态文件，找不到静态文件返回404

            response.writeHtml(request.getUrl());

        }else{//当在map集合找到映射时，传给out()方法转发给servlet

            this.out(request,response);
            response.writeHtml("servlet.html");//读取servlet页面

        }
    }

    public void start() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        ServerSocket serverSocket = new ServerSocket(port);//创建serverSocket对象，监听8888端口号

        System.out.println("my tomcat started on port:" + port);
        while (true) {
            this.initServlet();
            Socket socket = serverSocket.accept();//等待来自客户端的socket请求，获取socket对象
            System.out.println(socket);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Request request = new Request(inputStream);
            Response response = new Response(outputStream);

            this.select(request,response);

            inputStream.close();
            outputStream.close();
            socket.close();

        }
    }

    public static void main(String[] args) {
        TomcatLaunch tomcat = new TomcatLaunch();
        try {
            tomcat.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
