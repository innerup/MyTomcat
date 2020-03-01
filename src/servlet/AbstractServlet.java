package servlet;

public abstract class AbstractServlet {


    public abstract void doGet(Request request,Response response);

    public abstract void dopost(Request request,Response response);

    public void service(Request request,Response response){
        if(request.getMethod().equals("GET")){
            doGet(request,response);
        }
        else {
            dopost(request,response);
        }
    }

}
