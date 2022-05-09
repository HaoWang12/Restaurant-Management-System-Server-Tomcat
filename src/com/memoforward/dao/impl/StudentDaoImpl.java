package com.memoforward.dao.impl;

import com.memoforward.dao.StudentDao;
import com.memoforward.domain.Student;
import com.memoforward.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> findAllStudents() throws SQLException {
        String sql = "select * from user where statue='customer'";
        List<Student> stuList = new ArrayList<>();
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        stuList = qr.query(sql, new BeanListHandler<Student>(Student.class));
        C3P0Utils.closeConn();
        return stuList;
    }

    @Override
    public Student findStudentById(String sid) throws SQLException {
        String sql = "select * from user where username = ?";
        Student student = new Student();
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        student = qr.query(sql, new BeanHandler<Student>(Student.class),sid);
        C3P0Utils.closeConn();
        return student;
    }


    @Override
    public void updateStudent(Student stu) throws SQLException {
        String sql = "update user set password=? where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        Object[] params = {stu.getPassword(),stu.getUsername()};
        qr.update(sql, params);
        C3P0Utils.closeConn();
    }

    @Override
    public void deleteStudentById(String sid) throws SQLException {
        String sql = "delete from user where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, sid);
        C3P0Utils.closeConn();
    }
    @Override
    public void deleteIntoById(String sid) throws SQLException {
        String sql = "delete from intotable where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, sid);
        C3P0Utils.closeConn();
    }
    @Override
    public void deleteOutById(String sid) throws SQLException {
        String sql = "delete from outtable where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, sid);
        C3P0Utils.closeConn();
    }
    @Override
    public void deleteMemberById(String sid) throws SQLException {
        String sql = "delete from member where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, sid);
        C3P0Utils.closeConn();
    }
    @Override
    public void deleteNoticeById(String sid) throws SQLException {
        String sql = "delete from notice where username = ?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, sid);
        C3P0Utils.closeConn();
    }

    @Override
    public int findAllStudentNums() throws SQLException {
        String sql = "select count(*) from user where statue='customer'";
        Student student = new Student();
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        int num = ((Long) qr.query(sql, new ScalarHandler())).intValue();
        C3P0Utils.closeConn();
        return num;
    }

    @Override
    public List<Student> findStudentByPage(int currentPage,int perPageDataNum) throws SQLException {
        String sql = "select * from user where statue='customer' limit ?, ?";
        int _currentPage = (currentPage - 1) * perPageDataNum;
        List<Student> stuList = new ArrayList<Student>();
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        stuList = qr.query(sql, new BeanListHandler<Student>(Student.class),_currentPage,perPageDataNum);
        C3P0Utils.closeConn();
        return stuList;
    }

    @Override
    public List<Student> findStudentsByNum(String stuNum) throws SQLException {
//        String sql = "select * from user where snum like ?";
//        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<Student> stuList = new ArrayList<>();
//        stuList = qr.query(sql, new BeanListHandler<Student>(Student.class), "%" + stuNum + "%");
//        C3P0Utils.closeConn();
        return stuList;
    }

	@Override
	public void addStudent(Student student) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
