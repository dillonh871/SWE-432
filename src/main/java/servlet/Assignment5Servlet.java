package servlet;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "assignment5", urlPatterns = {"/assignment5"} )

public class Assignment5Servlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // HTTP POST request backend logic

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        PrintPostHead(out);
        PrintPostBody(out);
        PrintTail(out);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        PrintHead(out);
        PrintBody(out);
        PrintTail(out);
    }

    private void PrintHead(PrintWriter out) {
        out.println("<html>");
        out.println("");

        out.println("<head>");
        out.println("<style>body {background-color: powderblue;}</style>");
        out.println("<title>SWE 432 Long Hoang and Faiz Zia</title>");
        //button script
        out.println("<script>");
        out.println("	function addAndOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '&&';}");
        out.println("	function addOrOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '||';}");
        out.println("	function addNotOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '!';}");
        out.println("	function addlParOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '(';}");
        out.println("	function addrParOnClick(){");
        out.println("		document.getElementById(\"expression\").value += ')';}");
        out.println("	function addEqualOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '=';}");
        //check form
        out.println("	function checkForm(){");
        out.println("		var exp = document.myForm.expression;");
        out.println("		if (expression.value == \"\"){");
        out.println("		    alert (\"Missing Expression.\");");
        out.println("           return (false);");
        out.println("       }");
        out.println("		var vars = document.myForm.vars;");
        out.println("		if (vars.value == \"\"){");
        out.println(		   "alert (\"Missing Boolean Var(s).\");");
        out.println("		   return (false);");
        out.println("		}");
        out.println("		var vals = document.myForm.vals;");
        out.println("		if (vals.value == \"\"){");
        out.println("		    alert (\"Missing Value(s)\")");
        out.println("		    return (false);");
        out.println("		}");
        out.println("		return (true);");
        out.println("	}");
        out.println("</script>");

        out.println("</head>");
        out.println("");
    }

    private void PrintBody(PrintWriter out) {
        out.println("<body>");
        //instructions
        out.println("<h1 align=center>SWE 432 Assignment 5.</h1>");
        out.println("<h2 align=center>Long Hoang and Faiz Zia</h2>");
        out.println("<h2>Logic Predicate Form</h2>");
        out.println("<h3>Instructions</h3>");
        out.println("<p>1.For the Expression section, enter the expression that you would like to get the results of. You may name the boolean variables whatever you want and there is no limit on the number  boolean variables you want to use. Press on the button for the operator that you would like to use. You may also type the operator as well.");
        out.println("<br>");
        out.println("(Note that &&, ||, ^ are used for AND,OR and EXCLUSIVE OR and you may type whatever your choices. We also support multiple syntaxes such as &, AND, and, or, OR, etc.)");
        out.println("</p>");
        out.println("<p>2.For the Boolean Var(s) section, enter all the boolean variables you used and seperate them with a semicolon.");
        out.println("<br>");
        out.println("For example, if a user's expression was (tommorow || today) you would enter \"tommorow;today\".");
        out.println("</p>");
        out.println("<p>3.For the Value(s) section, enter values for the boolean variables and seperate them with a semicolon.Make sure to enter the values in the order corresponding to the boolean variable you want it for");
        out.println("<br>");
        out.println("For example, if the user has boolean variables \"tomorrow;today\" you can enter \"0;1\", \"T;F\", \"t;f\", \"true;false\", etc.");
        out.println("</p>");
        out.println("<p>4.Press the submit button when you are done entering your expression and variables</p>");

        //table
        out.println("<form method=\"post\" action=\"/assignment5\" name=\"myForm\" onSubmit=\"return checkForm()\">");
        out.println("	<table border=\"1\">");
        out.println("		<tr>");
        out.println("			<td><label for=\"expression\">Expression</label></td>");
        out.println("			<td><input type=\"text\" id=\"expression\" name=\"Expression\" value=\"\" size=\"46.5\" autocomplete=\"off\" ></td>");
        out.println("		</tr>");
        out.println("		<tr>");
        out.println("			<td><label for=\"vars\">Boolean Var(s):</label></td>");
        out.println("			<td><input type=\"text\" id=\"vars\" name=\"Boolean Variable(s)\" value=\"\" size=\"46.5\" autocomplete=\"off\" ></td>");
        out.println("		</tr>");
        out.println("		<tr>");
        out.println("			<td><label for=\"vars\">Value(s):</label></td>");
        out.println("			<td><input type=\"text\" id=\"vals\" name=\"Values(s)\" value=\"\" size=\"46.5\" autocomplete=\"off\" ></td>");
        out.println("		</tr>");
        out.println("		<table border=\"1\">");
        out.println("			<tr>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"&&\" id=\"andSign\" onclick=\"addAndOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"||\" id=\"orSign\" onclick=\"addOrOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"!\" id=\"notSign\" onclick=\"addNotOnClick()\">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("			<tr>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"(\" id=\"lPar\" onclick=\"addlParOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\")\" id=\"rPar\" onclick=\"addrParOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"=\" id=\"equalSign\" onclick=\"addEqualOnClick()\">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("		</table>");
        out.println("	</table>");
        out.println("	<input type=\"submit\" value=\"Submit\" align=\"center\">");
        out.println("</form>");
        out.println("<h2 align=\"center\">Collaboration Summary</h2>");
        out.println("<p>For this assignment, Long and Faiz collaborated on implementation ideas and how we thought the design for the Post web page should look like. We also both took some time going over how the algorithm for solving the predicates should be like. And afterwards Long and Faiz split up the work so we could accomplish the tasks in an efficient manner. Long started the assignment by setting up the servlet so both of us had something to work with. Long then worked on converting the flat HTML file we created into the doGet method and setting up all the print methods such as PrintHead, PrintBody, PrintTail, and PrintPostBody. Faiz then worked on converting our algorithm into the the doPost method and creating the table for the truth table. We then took some time afterwards to look over each other’s work and debug. This assignment gave us a lot of exposure on how to work with java servlets and the methods within them such as doGet and doPost.");
        out.println("</p>");
        out.println("</body>");
    }

    private void PrintPostHead(PrintWriter out) {
        out.println("<html>");
        out.println("");
        out.println("<head>");
        out.println("<style>body {background-color: powderblue;}</style>");
        out.println("<title>SWE 432 Long Hoang and Faiz Zia</title>");
        out.println("</head>");
        out.println("");
    }
    private void PrintPostBody(PrintWriter out) {
        out.println("<body>");
        out.println("<h1 align=center>SWE 432 Assignment 5.</h1>");
        out.println("<h2 align=center>Long Hoang and Faiz Zia</h2>");
        out.println("<h2 >Predicate Truth Table</h2>");
        out.println("<h3> This is your Predicate Truth Table based on the Expression(s), Variables, and Values you chose. </h3>");
        out.println("</body>");
    }

    private void PrintTail (PrintWriter out){
        out.println("");
        out.println("</html>");
    } 
}



