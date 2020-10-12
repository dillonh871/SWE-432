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

@WebServlet(name = "assginment5", urlPatterns = {"/assginment5"} )

public class Assignment5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

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
        // Import CSS
        out.println("</head>");
        out.println("");
    }

    private void PrintBody(PrintWriter out) {
        out.println("<body>");
        //instructions
        out.println("<h1 align=center>SWE 432 Assignment 3.</h1>");
        out.println("<h2 align=center>Long Hoang and Faiz Zia</h2>");
        out.println("<h2>Logic Predicate Form</h2>");
        out.println("<h3>Instructions</h3>");
        out.println("<p>1.For the Expression section, enter the expression that you would like to get the results of. You may name the boolean variables whatever you want and there is no limit on the number  boolean variables you want to use. Press on the button for the operator that you would like to use. You may also type the operator as well.");
        out.println("<br>");
        out.println("(Note that &&, ||, and ! are used for AND,OR and NOT and are the only available operators)");
        out.println("</p>");
        out.println("<p>2.For the Boolean Var(s) section, enter all the boolean variables you used and seperate them with a semicolon.");
        out.println("<br>");
        out.println("For example, if a user's expression was (tommorow || today) you would enter "tommorow;today".");
        out.println("</p>");
        out.println("<p>3.For the Value(s) section, enter values for the boolean variables and seperate them with a semicolon.Make sure to enter the values in the order corresponding to the boolean variable you want it for");
        out.println("<br>");
        out.println("For example, if the user has boolean variables "tomorrow;today" you can enter "0;1"");
        out.println("</p>");
        out.println("<p>4.Press the submit button when you are done entering your expression and variables</p>");

        //table
        out.println("<form method="post" action="https://cs.gmu.edu:8443/offutt/servlet/formHandler" name="myForm" onSubmit="return checkForm()">");
        out.println("	<table border="1">");
        out.println("		<tr>");
        out.println("			<td><label for="expression">Expression</label></td>");
        out.println("			<td><input type="text" id="expression" name="Expression" value="" size="46.5" autocomplete="off" ></td>");
        out.println("		</tr>");
        out.println("		<tr>");
        out.println("			<td><label for="vars">Boolean Var(s):</label></td>");
        out.println("			<td><input type="text" id="vars" name="Boolean Variable(s)" value="" size="46.5" autocomplete="off" ></td>");
        out.println("		</tr>");
        out.println("		<tr>");
        out.println("			<td><label for="vars">Value(s):</label></td>");
        out.println("			<td><input type="text" id="vals" name="Values(s)" value="" size="46.5" autocomplete="off" ></td>");
        out.println("		</tr>");
        out.println("		<table border="1">");
        out.println("			<tr>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value="&&" id="andSign" onclick="addAndOnClick()">");
        out.println("				</td>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value="||" id="orSign" onclick="addOrOnClick()">");
        out.println("				</td>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value="!" id="notSign" onclick="addNotOnClick()">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("			<tr>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value="(" id="lPar" onclick="addlParOnClick()">");
        out.println("				</td>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value=")" id="rPar" onclick="addrParOnClick()">");
        out.println("				</td>");
        out.println("				<td align="center" style="width: 146px;">");
        out.println("					<input type="button" value="=" id="equalSign" onclick="addEqualOnClick()">");
        out.println("				</td>");
        out.println("			</tr>");

        out.println("		</table>");
        out.println("	</table>");
        out.println("	<input type="submit" value="Submit" align="center">");
        out.println("</form>");
        out.println("<h2 align="center">Collaboration Summary</h2>");
        out.println("<p>For assignment 3, Long did some research, and then Long and Faiz got together to brainstorm how the webpage should be structured, and what functionalities we 
			would implement. We split up the parts of each task, so as to increase efficiency, as well as allow everyone to practice each aspect of the assignment. 
			Throughout the assignment, we continued to discuss with each other any issues we had, to learn from each other’s experience.");
        out.println("<br><br>");
        out.println("For the JavaScript requirements of the assignment, we decided to add form validation as well as string concatenation on a button click. For form validation, 
			Faiz added support to confirm that there was input from the user for the “Expression”,“Boolean Var(s)”, and "Value(s)" text boxes, and if there isn’t input 
			for one of those, it will alert the user through a pop-up alert.");
        out.println("<br><br>");
        out.println("Long implemented the string concatenation; the operator buttons concatenate operators to the expression field when clicked, so as to avoid errors by users in 
			terms of which operators are allowed, as well as avoid typos. This functionality supports multiple operators.
			This assignment gave us a lot of exposure to HTML, as well as JavaScript. It allowed us to explore and experiment with new parts of each.");
        out.println("</p>");
        out.println("</body>");
    }

    private void PrintTail (PrintWriter out){
        out.println("");
        out.println("</html>");
    } 
}



