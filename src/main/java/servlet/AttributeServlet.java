// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Import Java Libraries
import java.io.*;
import java.util.Enumeration;

@WebServlet(name = "attributeServlet", urlPatterns = {"/attribute"})
public class AttributeServlet extends HttpServlet
{

String lifeCycleURL = "/session";
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   // Get session object
   HttpSession session = request.getSession();

   String name   = request.getParameter("attrib_name");
   String value  = request.getParameter("attrib_value");
   String name2  = request.getParameter("attrib_name2");
   String color  = request.getParameter("attrib_color");

   String[] vals = {value, name2, color};
   String remove = request.getParameter("attrib_remove");

   if (remove != null && remove.equals("on"))
   {
      session.removeAttribute(name);
   }
   else
   {
      if ((name != null && name.length() > 0) && (value != null && value.length() > 0) && (name2 != null && name2.length() > 0) 
      && (color != null && color.length() > 0))
      {
         session.setAttribute(name, value);
         session.setAttribute(name2, color);
      }
   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   out.println("<html>");
   // no-cache lets the page reload by clicking on the reload link
   out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
   out.println("<head>");
   out.println(" <title>Session lifecycle</title>");
   out.println("</head>");
   out.println("");

   out.println("<body>");
   out.println("<h1><center>Long (Dillon) Hoang: Session attributes</center></h1>");

   out.println("Enter name and value, and another name and color of an attribute");


   String url = response.encodeURL("attribute");
   out.println("<form action=\"" + url + "\" method=\"GET\">");
   out.println(" Name: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value\">");
   out.println("<br>");

   out.println(" Name 2: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name2\">");

   out.println(" Color: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_color\">");

   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
   out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");
   out.println("</form>");
   out.println("<hr>");

   out.println("Attributes in this session:");
   Enumeration e = session.getAttributeNames();
   while (e.hasMoreElements())
   {
      String att_name2  = (String) e.nextElement();
      String att_color = (String) session.getAttribute(att_name2);
      String att_name  = (String) e.nextElement();
      String att_val = (String) session.getAttribute(att_name);

      out.print  ("<br><b>Name:</b> ");
      out.println(att_name);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_val);
      out.print  ("<br><b>Name 2:</b> ");
      out.println(att_name2);
      out.print  ("<br><b>Color:</b> ");
      out.println(att_color);
   } //end while

   out.print("<br><br><a href=\"" + lifeCycleURL + "?action=invalidate\">");
   out.println("Invalidate the session</a>");

   out.println("</body>");
   out.println("</html>");
   out.close();
} // End doGet
} //End  SessionLifeCycle
