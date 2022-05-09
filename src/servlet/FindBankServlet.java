package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/FindBankServlet")
public class FindBankServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public FindBankServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String result = Model.findBank(username);
        System.out.println(result);
        response.setCharacterEncoding("UTF-8");
        //返回客户端结果
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is post");
        doGet(request,response);
    }

}