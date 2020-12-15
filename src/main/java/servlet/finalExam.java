/** *****************************************************************
    finalExam.java   servlet example

        @author Long Hoang
        December 2020, final exam
********************************************************************* */

package servlet;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "finalExam", urlPatterns = {"/finalExam"} )

public class finalExam extends HttpServlet
{
/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Extracts the three strings
 *  Extracts the ordering for the concatenation
 *  Concatenates and sends the string back to the client
********************************************************* */
@Override
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   String result = "";
   PrintHead(out);
   PrintBody(out, result);
   PrintTail(out);
}

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
@Override
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out);
   PrintTail(out);
} // End doGet

/** *****************************************************
 *  Prints the <head> of the HTML page, no <body>.
********************************************************* */
private void PrintHead (PrintWriter out)
{
   out.println("<html>");
   out.println("");

   out.println("<head>");
   out.println("  <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
   out.println("  <title>Long Hoang Final Exam</title>");
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String result)
{
    out.println("<body>");
    out.println("<div>");

    out.println(" <div class=\"title\">");
    out.println("    <h1>Long Hoang Final Exam</h1>");
    out.println(" </div>");



    out.println(" <div class=\"container\">");
    out.println("    <div class=\"subcontainer\">");
    
    // Strings textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Enter Strings(Separate with new line).</div>");
    out.println("           <textarea name=\"stringsTA\"></textarea>");
    out.println("       </div>");

    //sorted list textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Sorted List of Strings</div>");
    out.println("           <textarea readonly name=\"stringsResultTA\"></textarea>");
    out.println("       </div>");

    //buttons
    out.println("       <div class=\"buttons-container\">");
    out.println("           <label class=\"block\"> <input type=\"radio\" /> Ascending </label>");
    out.println("           <label class=\"block\"> <input type=\"radio\" /> Descending </label>");
    out.println("           <button> Submit </button>");
    out.println("       </div>");

    out.println("    </div>");
    out.println(" </div>");

    out.println("</div>");

   out.println("");
   out.println("</body>");
} // End PrintBody

/** *****************************************************
 *  Overloads PrintBody (out) to print a page
 *  with blanks in the form fields.
********************************************************* */
private void PrintBody (PrintWriter out)
{
   PrintBody(out, "");
}

/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail


}  // End concatenateStrings
