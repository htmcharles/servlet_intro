package com.app.submission.submissionapp.controllers;

import java.io.*;
import java.time.LocalDate;

import com.app.submission.submissionapp.mdels.Student;
import com.app.submission.submissionapp.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class StudentRegistration extends HttpServlet {
    StudentService service = StudentService.getInstance();
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/form.jsp").forward(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        Student student = new Student(firstName,lastName,email,12, LocalDate.now());

        service.addStudent(student);
    }

    public void destroy() {
    }
}