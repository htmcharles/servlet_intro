package com.app.submission.submissionapp.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.app.submission.submissionapp.models.Student;
import com.app.submission.submissionapp.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentRegistration extends HttpServlet {
    private final StudentService service = StudentService.getInstance();
    private String message;

    public void init() {
        message = "Servlet initialized";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<Student> students = service.getAllStudents();
            request.setAttribute("students", students);
        } catch (Exception e) {
            request.setAttribute("error", "Error fetching student list: " + e.getMessage());
            e.printStackTrace(); // Log the error
        }
        request.getRequestDispatcher("WEB-INF/form.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String email = request.getParameter("email");

            // Validate input
            if (firstName == null || lastName == null || email == null ||
                    firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            Student student = new Student(firstName, lastName, email, 22, LocalDate.now());
            service.addStudent(student);
            request.setAttribute("message", "Student added successfully!");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Validation Error: " + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            e.printStackTrace(); // Log the error
        }

        doGet(request, response); // Refresh the student list after adding
    }

    public void destroy() {
        // Cleanup resources if needed
    }
}
