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
@WebServlet("/DeleteDeskServlet")
public class DeleteDeskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public DeleteDeskServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        
        boolean result= Model.deleteDesk(id);
        
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


