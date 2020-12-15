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
   out.println("  <style>body {background-color: powderblue;}</style>");
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
    out.println("<div>");

    out.println(" <div className=\"title\"");
    out.println("    <h2>Long Hoang Final Exam</h2>");
    out.println(" </div>");



    out.println(" <div className=\"container\"");
    out.println("    <div className=\"subcontainer\"");
    
    
    out.println("       <div className=\"textbox\"");
    out.println("           <div className=\"boxone-title\">Enter Strings(Separate with new line).</div>");
    out.println("           <textarea name=\"stringsTA\"></textarea>");
    out.println("       </div>");

    out.println("       <div className=\"textbox\"");
    out.println("           <div className=\"boxone-title\">Sorted List of Strings</div>");
    out.println("           <textarea name=\"stringsResultTA\"></textarea>");
    out.println("       </div>");


    out.println("    </div>");
    out.println(" </div>");

    out.println("</div>");

   out.print  ("<form name=\"listForm\" method=\"post\" action=\"/finalExam\"");
   out.println(" <table>");
   out.println("  <tr>");
   out.println("   <td>String A:");
   out.println("   <td><input type=\"text\" name=\"STRINGA\" value=\"Item\" size=10>");
   out.println("  </tr>");
   out.println(" </table>");

   out.println(" <table><tr>");
   out.println(" <td>Choose sorting order:</td>");
   out.println(" <td><label><input checked type=\"radio\" id=\"sorttype\" name=\"sorttype\" value=\"none\">none</label>&nbsp;&nbsp;&nbsp;</td>");
   out.println(" <td><label><input checked type=\"radio\" id=\"sorttype\" name=\"sorttype\" value=\"ascending\">ascending</label>&nbsp;&nbsp;&nbsp;</td>");
   out.println(" <td><label><input checked type=\"radio\" id=\"sorttype\" name=\"sorttype\" value=\"descending\">descending</label>&nbsp;&nbsp;&nbsp;</td>");
   out.println(" </tr></table>");

   out.println("Keep only unqiue strings?");
   out.println(" <label><input checked type=\"radio\" id=\"uniqe\" name=\"unique\" value=\"no\">No</label>");
   out.println(" <label><input checked type=\"radio\" id=\"unique\" name=\"unique\" value=\"yes\">Yes</label>");

   out.println(" <br/>");
   out.println(" <br/>");
   out.println(" <input type=\"submit\" value=\"Sort\" name=\"sort\">");
   out.println("</form>");
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
