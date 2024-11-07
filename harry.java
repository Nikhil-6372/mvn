package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {
    private List<String> tasks = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<ul>");
        for (String task : tasks) {
            out.println("<li>" + task + " <a href='todo?delete=" + task + "'>Delete</a></li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if (task != null && !task.isEmpty()) {
            tasks.add(task);
        }
        response.sendRedirect("todo");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskToDelete = request.getParameter("delete");
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
        }
        response.sendRedirect("todo");
    }
}
