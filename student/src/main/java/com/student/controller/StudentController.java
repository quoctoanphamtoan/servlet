/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.dao.StudentDao;
import com.student.model.Student;
/**
 *
 * @author TriPham
 */
@MultipartConfig(maxFileSize = 16177215)   
@WebServlet(urlPatterns = {"/"})
public class StudentController extends HttpServlet {

     private static final long serialVersionUID = 1L;
    private StudentDao studentDao;

    public void init() {
        studentDao = new StudentDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	 String action = request.getServletPath();
    	 if(!action.equalsIgnoreCase("/addStudent")) {
    		 System.out.println("Ko co");
    	 }
    	 System.out.println(action);
    	 try {
			insertStudent(request, response);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
       rd.forward(request, response);
       
    }
   
    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Student > listCustomer = studentDao.getAllStudent();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingCustomer = studentDao.getCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-form.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        
              
        Student student = new Student(firstName, lastName, email);
        studentDao.saveStudent(student, inputStream);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

//    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, IOException, ServletException {
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//        InputStream inputStream = null; // input stream of the upload file
//         
//        // obtains the upload file part in this multipart request
//        Part filePart = request.getPart("photo");
//        if (filePart != null) {
//            // obtains input stream of the upload file
//            inputStream = filePart.getInputStream();
//        }
//        Customer customer = new Customer(id, name, email, country);
//        studentDao.updateCustomer(customer, inputStream);
//        response.sendRedirect("list");
//    
//    }
//
//    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        studentDao.deleteCustomer(id);
//        response.sendRedirect("list");
//    }
//    private void viewCustomer(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, IOException, ServletException {
//      
//        int id = Integer.parseInt(request.getParameter("id"));
//        
//        CustomerDTO customerDTO = studentDao.getCustomerDTO(id);
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//        request.setAttribute("customer", customerDTO);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-view.jsp");
//        dispatcher.forward(request, response);
//    }

}
