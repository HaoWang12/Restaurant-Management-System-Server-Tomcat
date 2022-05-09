package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/AddPurchaseServlet")
public class AddPurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AddPurchaseServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
        String money = request.getParameter("money");
        String time = request.getParameter("time");
        String note = request.getParameter("note");
        
        boolean result= Model.addPurchase(content, money, time, note);
        
        //通过PrintWriter返回给客户端操作结果
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is post");
        doGet(request, response);
    }

}


