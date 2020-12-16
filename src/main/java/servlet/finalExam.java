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
    String userStrings = request.getParameter("stringsTA"); // get strings from user input
    String sortOption = request.getParameter("radioAD");  // get the sort option

    // sorts the list alphabetically 
    String[] cleanedList = userStrings.split("\\r?\\n");
    ArrayList<String> stringList = new ArrayList<>();
    for(int i = 0; i < cleanedList.length; i++){
        stringList.add(cleanedList[i]);
    }
    Collections.sort(stringList);
    if(sortOption.equals("desc")){
        Collections.reverse(stringList);
    }

    String sortedList = "";
    for(String s : stringList){
        sortedList += s + "\n";
    }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   PrintHead(out);
   PrintBody(out, sortedList);
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
   out.println("  <style>.title {text-align: center;}</style>");

   out.println("  <style>.container{display: flex;justify-content: center;}</style>");
   out.println("  <style>.sub-container{display: flex;flex-direction: row;align-items: center;justify-content: space-evenly;}</style>");
   out.println("  <style>.textbox{align-items: center;display: flex;flex-direction: column;width: 300px;height: 450px;}</style>");
    out.println("  <style>.box-title{text-align: center;}</style>");
   out.println("  <style>textarea {align-items: center;display: flex;flex-direction: column;width: 250px;height: 400px;border-radius: 15px;overflow: auto;}</style>");


    out.println("  <style>.buttons-container{display: flex; flex-Direction: column;align-items: center;justify-content: center;}</style>");
    out.println("  <style>button {display: block;}</style>");
    out.println("  <style>.block {display: block;}</style>");

   out.println("  <title>Long Hoang Final Exam</title>");
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out, String sortedList)
{
    out.println("<body>");
    out.println("<div>");

    out.println(" <div class=\"title\">");
    out.println("    <h1>Long Hoang Final Exam</h1>");
    out.println(" </div>");

    out.println("<form method=\"post\" action=\"/finalExam\" name=\"myForm\">");
    out.println(" <div class=\"container\">");
    out.println("    <div class=\"sub-container\">");
    
    // Strings textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Enter Strings(Separate with new line).</div>");
    out.println("           <textarea name=\"stringsTA\"></textarea>");
    out.println("       </div>");

    //sorted list textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Sorted List of Strings</div>");
    if(!sortedList.isEmpty()){
        out.println("           <textarea readonly name=\"stringsResultTA\">" + sortedList + "</textarea>");
    }
    else{
        out.println("           <textarea readonly name=\"stringsResultTA\"></textarea>");
    }
    out.println("       </div>");

    //buttons
    out.println("       <div class=\"buttons-container\">");
    
    out.println("           <label class=\"block\"> <input type=\"radio\" name=\"radioAD\" value=\"asc\"/>   Ascending </label>");
    out.println("           <label class=\"block\"> <input type=\"radio\" name=\"radioAD\" value=\"desc\" /> Descending </label>");
    out.println("	        <input class=\"block\" type=\"submit\" value=\"Submit\" align=\"center\">");
    out.println("	        <input class=\"block\" type=\"reset\" value=\"Reset\" align=\"center\">");
    out.println("       </div>");

    out.println("    </div>");
    out.println(" </div>");
    out.println("</form>");
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
