package com.app.submission.submissionapp.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.app.submission.submissionapp.models.Student;
import com.app.submission.submissionapp.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class StudentRegistration extends HttpServlet {
    StudentService service = StudentService.getInstance();
    private String message;

    public void init() {
        message = "Servlet init";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Student> students = service.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("WEB-INF/form.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        Student student = new Student(firstName, lastName, email, 22, LocalDate.now());

        service.addStudent(student);
        request.setAttribute("message", "Student added successfully!");


        doGet(request, response); // Refresh the student list after adding
    }

    public void destroy() {
    }

}
