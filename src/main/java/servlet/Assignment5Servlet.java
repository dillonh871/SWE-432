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

@WebServlet(name = "assignment5", urlPatterns = {"/assignment5"} )

public class Assignment5Servlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // HTTP POST request backend logic
        // HTTP POST request backend logic
        String expression = request.getParameter("Expression"); //Gets the expression input
        String vars = request.getParameter("Boolean Variable(s)"); //Gets the Variable names input
        String vals = request.getParameter("Values(s)"); //Gets the Variable values input
        System.out.println("vars" + vars);
        int op; //1: AND, 2: OR, 3: NOT, 4: XOR
        String posFormat, negFormat;
        String[] rsltTbl;

        //String[] expArr = expression.split(";");
        String[] varsArr = vars.split(";");
        String[] valsArr = vals.split(";");

        op = 0;
        //EVALUATE AND STORE THE OPERATOR'S VALUE 
        if(expression.contains("AND"))
        { op = 1; }

        else if(expression.contains("and"))
        { op = 1; }

        else if(expression.contains("&"))
        { op = 1; }

        else if(expression.contains("&&"))
        { op = 1; }

        else if(expression.contains("or"))
        { op = 2; }

        else if(expression.contains("OR"))
        { op = 2; }

        else if(expression.contains("|"))
        { op = 2; }

        else if(expression.contains("||"))
        { op = 2; }

        else if(expression.contains("XOR"))
        { op = 4; }

        else if(expression.contains("^"))
        { op = 4; }


        posFormat = "";
        negFormat = "";
        //EVALUATE AND STORE THE VARIABLE INPUT TYPE - FALSE
        if(valsArr[0].equals("0") || valsArr[0].equals("1"))
        { posFormat = "1"; negFormat = "0"; }

        else if(valsArr[0].equals("f") || valsArr[0].equals("t"))
        { posFormat = "t"; negFormat = "f"; }

        else if(valsArr[0].equals("F") || valsArr[0].equals("T") )
        { posFormat = "T"; negFormat = "F"; }
        
        else if(valsArr[0].equals("false") || valsArr[0].equals("true") )
        { posFormat = "true"; negFormat = "false"; }

        else if(valsArr[0].equals("False") || valsArr[0].equals("True"))
        { posFormat = "True"; negFormat = "False"; }

        else if(valsArr[0].equals("FALSE") || valsArr[0].equals("TRUE"))
        { posFormat = "TRUE"; negFormat = "FALSE"; }

        rsltTbl = new String[12];
        rsltTbl[0] = posFormat;
        rsltTbl[1] = posFormat;
        rsltTbl[2] = " ";
        rsltTbl[3] = posFormat;
        rsltTbl[4] = negFormat;
        rsltTbl[5] = " ";
        rsltTbl[6] = negFormat;
        rsltTbl[7] = posFormat;
        rsltTbl[8] = " ";
        rsltTbl[9] = negFormat;
        rsltTbl[10] = negFormat;
        rsltTbl[11] = " ";


        if(op == 1) //AND CASE
        {
        rsltTbl[2] = posFormat;
        rsltTbl[5] = negFormat;
        rsltTbl[8] = negFormat;
        rsltTbl[11] = negFormat;
        }

        if(op == 2) //OR CASE
        {
        rsltTbl[2] = posFormat;
        rsltTbl[5] = posFormat;
        rsltTbl[8] = posFormat;
        rsltTbl[11] = negFormat;
        }

        if(op == 4) //XOR CASE
        {
        rsltTbl[2] = negFormat;
        rsltTbl[5] = posFormat;
        rsltTbl[8] = posFormat;
        rsltTbl[11] = negFormat;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        PrintPostHead(out);
        PrintPostBody(out, varsArr, rsltTbl);
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
        out.println("		document.getElementById(\"expression\").value += '^';}");
        out.println("	function addSingleAndOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '&';}");
        out.println("	function addSingleOrOnClick(){");
        out.println("		document.getElementById(\"expression\").value += '|';}");
        out.println("	function addWordXOROnClick(){");
        out.println("		document.getElementById(\"expression\").value += 'XOR';}");
        out.println("	function addWordAndOnClick(){");
        out.println("		document.getElementById(\"expression\").value += 'AND';}");
        out.println("	function addWordOrOnClick(){");
        out.println("		document.getElementById(\"expression\").value += 'OR';}");

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
        out.println("<p>1.For the Expression section, enter the expression that you would like to get the results of. You may name the boolean variables whatever you want and you may only use ONE operator. We have provided some operator buttons for you to use but you are not limited to those. You may also TYPE the operator as well.");
        out.println("<br>");
        out.println("(Notes: &&, ||, ^ are used for AND,OR and EXCLUSIVE OR and they are your only operator choices. We also support multiple syntaxes such as &, |, AND, OR, XOR. We also DO NOT support using parentheses in the predicates.");
        out.println("</p>");
        out.println("<p>2.For the Boolean Var(s) section, enter all the boolean variables you used and seperate them with a semicolon.");
        out.println("<br>");
        out.println("For example, if a user's expression was \"tommorow || today\" you would enter \"tommorow;today\".");
        out.println("</p>");
        out.println("<p>3.For the Value(s) section, enter values for the boolean variables and seperate them with a semicolon.Make sure to enter the values in the order corresponding to the boolean variable you want it for");
        out.println("<br>");
        out.println("For example, if the user has boolean variables \"tomorrow;today\" you can enter \"1;0\", \"T;F\", \"t;f\", \"True;False\", \"true;false\", \"TRUE;FALSE\"");
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
        out.println("					<input type=\"button\" value=\"^\" id=\"notSign\" onclick=\"addNotOnClick()\">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("			<tr>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"&\" id=\"andSingleSign\" onclick=\"addSingleAndOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"|\" id=\"orSingleSign\" onclick=\"addSingleOrOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"XOR\" id=\"xorSign\" onclick=\"addWordXOROnClick()\">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("			<tr>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"AND\" id=\"andWordSign\" onclick=\"addWordAndOnClick()\">");
        out.println("				</td>");
        out.println("				<td align=\"center\" style=\"width: 146px;\">");
        out.println("					<input type=\"button\" value=\"OR\" id=\"orWordSign\" onclick=\"addWordOrOnClick()\">");
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
    private void PrintPostBody(PrintWriter out, String[] varsArr, String[] rsltTbl) {
        out.println("<body>");
        out.println("<h1 align=center>SWE 432 Assignment 5.</h1>");
        out.println("<h2 align=center>Long Hoang and Faiz Zia</h2>");
        out.println("<h2 >Predicate Truth Table</h2>");
        out.println("<h3> This is your Predicate Truth Table based on the Expression(s), Variables, and Values you chose. </h3>");

        out.println("<table border=\"1\">");

        out.println("  <tr>");
        out.println("    <td><b>Row</b></td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\"><b>" + varsArr[0] + "</b></td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\"><b>" + varsArr[1] + "</b></td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\"><b>Result</b></td>");
        out.println("  </tr>");

        out.println("  <tr>");
        out.println("    <td>1.</td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[0] + "</td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[1] + "</td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[2] + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td>2.</td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[3] + "</t>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[4] + "</td>");
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[5] + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td>3.</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[6] + "</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[7] + "</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[8] + "</td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td>4.</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[9] + "</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[10] + "</td>");
        out.println("    <th align=\"center\" style=\"width: 100px;\">" + rsltTbl[11] + "</td>");
        out.println("  </tr>");
        out.println("</table>");

        out.println("<h2 align=\"center\">Collaboration Summary</h2>");
        out.println("<p>For this assignment, Long and Faiz collaborated on implementation ideas and how we thought the design for the Post web page should look like. We also both took some time going over how the algorithm for solving the predicates should be like. And afterwards Long and Faiz split up the work so we could accomplish the tasks in an efficient manner. Long started the assignment by setting up the servlet so both of us had something to work with. Long then worked on converting the flat HTML file we created into the doGet method and setting up all the print methods such as PrintHead, PrintBody, PrintTail, and PrintPostBody. Faiz then worked on converting our algorithm into the the doPost method and creating the table for the truth table. We then took some time afterwards to look over one another's work and debug. This assignment gave us a lot of exposure on how to work with java servlets and the methods within them such as doGet and doPost.");
        out.println("</p>");
        out.println("</body>");
    }

    private void PrintTail (PrintWriter out){
        out.println("");
        out.println("</html>");
    } 
}



