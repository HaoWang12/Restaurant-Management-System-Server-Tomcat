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
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AddRoomServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        request.setCharacterEncoding("UTF-8");
        String phone = request.getParameter("phone");
        String number = request.getParameter("number");
        String time = request.getParameter("time");
        String note = request.getParameter("note");
        
        boolean result= Model.addRoom(phone, number, time, note);
        
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


