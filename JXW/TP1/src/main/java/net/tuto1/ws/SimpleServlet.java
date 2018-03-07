package net.tuto2.ws;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(name="FirstServlet", urlPatterns={"/test"}, loadOnStartup=1) 

public class SimpleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println( "Hello World from annotated servlet\n");
        out.flush();
        out.close();
    }  
}