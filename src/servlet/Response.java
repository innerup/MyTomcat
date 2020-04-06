package servlet;


import httpresolver.HttpResponse;

import java.io.*;

/**
 * 从web文件夹下获取的html文件会通过response处理返回为一个response响应
 */
public class Response {

    private OutputStream outputStream;
    static final File WEB_ROOT = new File("D:\\java-2019-09\\JAVA\\javaclassOfEclipse\\MyTomcat\\src\\web");

    public Response(OutputStream outputStream){

        this.outputStream = outputStream;

    }

    public void writeHtml(String path) throws IOException {
        byte[] bytes = new byte[2048];
        File file = new File(WEB_ROOT,path);
        FileInputStream fis= null;
        try{
            if (file.exists()) {
                fis = new FileInputStream(file);//读取文件
                StringBuilder heads=new StringBuilder("HTTP/1.1 200 OK\r\n");
                heads.append("Content-Type: text/html\r\n");
                StringBuilder body=new StringBuilder();
                int len ;
                while ((len=fis.read(bytes, 0, 1024)) != -1) {
                    body.append(new String(bytes,0,len));//添加文件
                }

                heads.append(String.format("Content-Length: %d\n",body.toString().getBytes().length));
                heads.append("\r\n");
                outputStream.write(heads.toString().getBytes());
                outputStream.write(body.toString().getBytes());
            } else {
                HttpResponse.response404(outputStream);
            }
        }catch (FileNotFoundException e){
            HttpResponse.response404(outputStream);
        }finally {
            if (file.exists()){
                fis.close();
            }
        }
    }


}
