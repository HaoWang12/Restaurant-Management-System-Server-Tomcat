package com.memoforward.web.servlet;

import com.memoforward.domain.PageBean;
import com.memoforward.domain.Student;
import com.memoforward.service.StudentService;
import com.memoforward.service.impl.StudentServiceImpl;
import com.memoforward.utils.MyBeanUtils;
import com.memoforward.utils.UUIDUtils;
import com.memoforward.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/studentServlet.do")
public class StudentServlet extends BaseServlet {


    public String editStudent(HttpServletRequest req, HttpServletResponse resp){
        StudentService stuService = new StudentServiceImpl();
        String sid = req.getParameter("sid");
        Student student = null;
        try {
            student = stuService.findStudentById(sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("student",student);
        return "/jsp/editStu.jsp";
    }

    public String updateStudent(HttpServletRequest req, HttpServletResponse resp){
        Student stu = new Student();
        StudentService stuService = new StudentServiceImpl();
        try {
        	String sid = req.getParameter("sid");
        	String password = req.getParameter("password");
        	stu.setPassword(password);
        	stu.setUsername(sid);
        	
            stuService.updateStudent(stu);
            resp.sendRedirect("indexServlet.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteStudent(HttpServletRequest req, HttpServletResponse resp){
        String sid = req.getParameter("sid");
        StudentService stuService = new StudentServiceImpl();
        try {
            stuService.deleteStudentById(sid);
            resp.sendRedirect("indexServlet.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findStudentByPage(HttpServletRequest req, HttpServletResponse resp){
        StudentService stuService = new StudentServiceImpl();
        int currentPageNum;
        String page = req.getParameter("currentPageNum");
        currentPageNum = Integer.valueOf(page);
        PageBean<Student> pageList= null;
        try {
            pageList = stuService.findStudentsByPage(currentPageNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pageList", pageList);

        return "/jsp/stuList.jsp";
    }

    public String queryStudentByStuNum(HttpServletRequest req, HttpServletResponse resp){
        String stuNum = req.getParameter("stuNum");
        System.out.println(stuNum);
        StudentService stuService = new StudentServiceImpl();
        List<Student> stuList = null;
        try {
            stuList = stuService.findStudentsByNum(stuNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("stuList", stuList);
        return "/jsp/queryStuList.jsp";
    }

}
