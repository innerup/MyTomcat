package servlet;

public class Servlet extends AbstractServlet {

    @Override
    public void doGet(Request request, Response response) {

        System.out.println("get");

    }

    @Override
    public void dopost(Request request, Response response) {

        System.out.println("post");

    }
}
