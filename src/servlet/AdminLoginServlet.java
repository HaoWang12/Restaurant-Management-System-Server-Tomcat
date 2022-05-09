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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AdminLoginServelt")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AdminLoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		
        //获取客户端传递值
        String loginAccount = request.getParameter("a_username");
        String loginPassword = request.getParameter("a_password");
        boolean result = Model.loginAdmin(loginAccount, loginPassword);
        if(result == true) {
        	request.getRequestDispatcher("indexServlet.do").forward(request,response);
        }else {
        	request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
		}
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is post");
        doGet(request,response);
    }

}