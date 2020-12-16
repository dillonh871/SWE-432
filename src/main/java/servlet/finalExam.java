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
    String sortOption = request.getParameter("radioAS");// get the sort option (alphabetical or string length)
    String orderOption = request.getParameter("radioAD");  // get the order option (Ascend or Descend)
    String uniqueOption = request.getParameter("radioUniq"); // get unqiue option (Unique strings only?)
    

    //removes newline from strings
    String[] cleanedList = userStrings.split("\\r?\\n");

    ArrayList<String> stringList = new ArrayList<>();
    //move strings to array list 
    for(int i = 0; i < cleanedList.length; i++){
        stringList.add(cleanedList[i]);
    }

    //alphabetical sorting
    if(sortOption.equals("alpha")){
        Collections.sort(stringList);
        if(orderOption.equals("desc")){
            Collections.reverse(stringList);
        }
    }

    //string length sorting 
    else{
        if(orderOption.equals("desc")){
            stringList.sort(Comparator.comparingInt(String::length).reversed());
        }
        else{
            stringList.sort(Comparator.comparingInt(String::length));
        }
    }

    String sortedList = "";
    if(uniqueOption.equals("unique")){
        //linkedHashSet removes duplicates and perserves the order of the list
        LinkedHashSet<String> uniqueStrings = new LinkedHashSet<String>(stringList);
        for(String s : uniqueStrings){
            sortedList += s + "\n";
        }
    }
    else{
        for(String s : stringList){
            sortedList += s + "\n";
        }
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
    out.println("  <style>h3 {margin-bottom: 2px;}</style>");
    out.println("  <style>h4 {margin-top: 2px;}</style>");
    out.println("  <style>h4 {margin-bottom: 0px;}</style>");
    out.println("  <style>p {margin-top: 0px;}</style>");


        //clear the lists
        out.println("<script>");
        out.println("	function clearAll(){");
        out.println("		var srlt = document.getElementById(\"stringsResultID\");");
        out.println("		var sinput = document.getElementById(\"stringsID\");");
        out.println("		srlt.value = \'\'; sinput.value = \'\';");
        out.println("	}");

        //input validation
        out.println("	function checkForm(){");
        out.println("		var sinput = document.getElementById(\"stringsID\");");
        out.println("		if (sinput.value == \'\'){");
        out.println("		    alert (\"Please Enter String(s) to be sorted!\");");
        out.println("           return (false);");
        out.println("       }");
        out.println("		return (true);");
        out.println("	}");
        out.println("</script>");

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

        out.println("<h3>Additional notes on how program works</h3>");
        out.println("<h4>Alphabetical Ordering</h4>");
        out.println("<p>1.Alphabetical Ascending -  alphabetical ordering going from A-Z");
        out.println("<br>");
        out.println("2.Alphabetical Descending - alphabetical ordering going from Z-A</p>");

        out.println("<h4>String Length Ordering</h4>");
        out.println("<p>1.String Length Ascending - least number of characters to most");
        out.println("<br>");
        out.println("2.String Length Descending -  most number of characters to least");
        out.println("<br>");
        out.println("Note: In the case that multiple strings have the same length the program will randomly decide which one goes first</p>");
        out.println("<br>");

    out.println("<form method=\"post\" action=\"/finalExam\" name=\"myForm\">");
    out.println(" <div class=\"container\">");
    out.println("    <div class=\"sub-container\">");
    
    // Strings textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Enter Strings(Separate with new line).</div>");
    out.println("           <textarea id=\"stringsID\" name=\"stringsTA\"></textarea>");
    out.println("       </div>");

    //sorted list textbox
    out.println("       <div class=\"textbox\">");
    out.println("           <div class=\"boxone-title\">Sorted List of Strings</div>");
    if(!sortedList.isEmpty()){
        out.println("           <textarea readonly name=\"stringsResultTA\" id=\"stringsResultID\">" + sortedList + "</textarea>");
    }
    else{
        out.println("           <textarea readonly name=\"stringsResultTA\" id=\"stringsResultID\"></textarea>");
    }
    out.println("       </div>");

    //buttons
    out.println("       <div class=\"buttons-container\">");

    //Alpbetical or string length
    out.println("           <div class=\"boxone-title\">Pick how you want to sort the list: </div>");
    out.println("           <label > <input type=\"radio\" name=\"radioAS\" value=\"alpha\" checked/> Alphabetical </label>");
    out.println("           <label > <input type=\"radio\" name=\"radioAS\" value=\"slength\" /> String Length </label>");
    out.println("<br>");

    //ascending or descending
    out.println("           <div class=\"boxone-title\">Pick how you want the list to be ordered: </div>");
    out.println("           <label > <input type=\"radio\" name=\"radioAD\" value=\"asc\" checked/>   Ascending </label>");
    out.println("           <label > <input type=\"radio\" name=\"radioAD\" value=\"desc\" /> Descending </label>");
    out.println("<br>");

    // unique strings only?
    out.println("           <div class=\"boxone-title\">Unique Strings Only?</div>");
    out.println("           <label > <input type=\"radio\" name=\"radioUniq\" value=\"notunique\" checked/> No </label>");
    out.println("           <label > <input type=\"radio\" name=\"radioUniq\" value=\"unique\" /> Yes </label>");
    out.println("<br>");

    out.println("	        <input class=\"block\" type=\"submit\" value=\"Submit\" align=\"center\" onclick=\"return checkForm()\">");
    out.println("	        <input class=\"block\" type=\"button\" id=\"Clear\" value=\"Clear\" align=\"center\" onclick=\"return clearAll()\">");
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


} 
