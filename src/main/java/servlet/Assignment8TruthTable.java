package servlet;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


@WebServlet(name = "assignment8_TruthTable", urlPatterns = {"/assignment8_TruthTable"} )

public class Assignment8TruthTable extends HttpServlet {
    static enum Data {EXPRESS, VARS, VALS, ENTRY, ENTRIES};

    static String RESOURCE_FILE = "entries.xml";

    static String Domain  = "";
    static String Path    = "/";
    static String Servlet = "assignment8_TruthTable";

    public class Entry {
        String pexpression;
        String pvars;
        String pvals;
    }

    public List<Entry> entries;

    public class EntryManager {
        private String filePath = null;
        private XMLEventFactory eventFactory = null;
        private XMLEvent LINE_END = null;
        private XMLEvent LINE_TAB = null;
        private XMLEvent ENTRIES_START = null;
        private XMLEvent ENTRIES_END = null;
        private XMLEvent ENTRY_START = null;
        private XMLEvent ENTRY_END = null;

        public EntryManager(){
        eventFactory = XMLEventFactory.newInstance();
        LINE_END = eventFactory.createDTD("\n");
        LINE_TAB = eventFactory.createDTD("\t");

        ENTRIES_START = eventFactory.createStartElement(
            "","", Data.ENTRIES.name());
        ENTRIES_END =eventFactory.createEndElement(
            "", "", Data.ENTRIES.name());
        ENTRY_START = eventFactory.createStartElement(
            "","", Data.ENTRY.name());
        ENTRY_END =eventFactory.createEndElement(
            "", "", Data.ENTRY.name());
        }

        public void setFilePath(String filePath) {
        this.filePath = filePath;
        }

        public List<Entry> save(String pexpression, String pvars, String pvals)
        throws FileNotFoundException, XMLStreamException{
        List<Entry> entries = getAll();
        Entry newEntry = new Entry();
        newEntry.pexpression = pexpression;
        newEntry.pvars = pvars;
        newEntry.pvals = pvals;
        entries.add(newEntry);

        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(filePath));

        eventWriter.add(eventFactory.createStartDocument());
        eventWriter.add(LINE_END);

        eventWriter.add(ENTRIES_START);
        eventWriter.add(LINE_END);

        for(Entry entry: entries){
            addEntry(eventWriter, entry.pexpression, entry.pvars, entry.pvals);
        }

        eventWriter.add(ENTRIES_END);
        eventWriter.add(LINE_END);

        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
        return entries;
        }

        private void addEntry(XMLEventWriter eventWriter, String pexpression, String pvars, String pvals) throws XMLStreamException {
            eventWriter.add(ENTRY_START);
            eventWriter.add(LINE_END);
            createNode(eventWriter, Data.EXPRESS.name(), pexpression);
            createNode(eventWriter, Data.VARS.name(), pvars);
            createNode(eventWriter, Data.VALS.name(), pvals);
            eventWriter.add(ENTRY_END);
            eventWriter.add(LINE_END);

        }

        private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(LINE_TAB);
        eventWriter.add(sElement);

        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);

        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(LINE_END);
        }

        public List<Entry> getAll(){
            List entries = new ArrayList();

            try{
                File file = new File(filePath);
                if(!file.exists()){
                return entries;
            }

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(file);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            Entry entry = null;
            while (eventReader.hasNext()) {
            // <ENTRIES> not needed for the example
            XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals(Data.ENTRY.name())) {
                        entry = new Entry();
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(Data.EXPRESS.name())) {
                        event = eventReader.nextEvent();
                        entry.pexpression =event.asCharacters().getData();
                        continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(Data.VARS.name())) {
                        event = eventReader.nextEvent();
                        entry.pvars =event.asCharacters().getData();
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(Data.VALS.name())) {
                        event = eventReader.nextEvent();
                        entry.pvals =event.asCharacters().getData();
                        continue;
                    }
                }

                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(Data.ENTRY.name())) {
                        entries.add(entry);
                }
            }

            }

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (XMLStreamException e) {
                e.printStackTrace();
            }catch(IOException ioException){
                ioException.printStackTrace();
            }

            return entries;
        }

        public String getAllAsHTMLTable(List<Entry> entries){
            StringBuilder htmlOut = new StringBuilder("<table border=\"1\">");
            htmlOut.append("<tr><th>Option(s)</th><th>Expression</th><th>Variables</th><th>Values</th></tr>");
            if(entries == null || entries.size() == 0){
                htmlOut.append("<tr><td>No entries yet.</td></tr>");
            }else{
                int i = 0;
                for(Entry entry: entries){
                    htmlOut.append("<tr><td align=\"center\">"+i+"</td><td align=\"center\">"+entry.pexpression+"</td><td align=\"center\">"+entry.pvars+"</td><td align=\"center\">"+entry.pvals+"</td></tr>");
                    i++;
                }
            }   
            htmlOut.append("</table>");
            return htmlOut.toString();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Entry> globalList = (ArrayList<Entry>)request.getAttribute("globalList");
        String action = request.getParameter("myForm");
        int optionNum = 0;

        String expression = request.getParameter("Expression"); //Gets the expression input
        String vars = request.getParameter("Boolean Variable(s)"); //Gets the Variable names input
        String vals = request.getParameter("Values(s)"); //Gets the Variable values input

        if(request.getParameter("Predicate") != null){
            EntryManager entryManager = new EntryManager();
            entryManager.setFilePath(RESOURCE_FILE);
            // List<Entry> newEntries= null;

            // try{
            //     newEntries=entryManager.save(expression, vars, vals);
            // }catch(FileNotFoundException e){
            //     e.printStackTrace();
            // }
            // catch(XMLStreamException e){
            //     e.printStackTrace();
            // }

            String optionNumString = request.getParameter("Predicate");
            optionNum = Integer.parseInt(optionNumString);

            expression = globalList.get(optionNum).pexpression;
            vars =  globalList.get(optionNum).pvars;
            vals =  globalList.get(optionNum).pvals;    
        }
        // HTTP POST request backend logic

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
        out.println("<h1 align=center>SWE 432 Assignment 8.</h1>");
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
        out.println("    <td align=\"center\" style=\"width: 100px;\">" + rsltTbl[3] + "</td>");
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
        out.println("<p>For this assignment, Long and Faiz both brainstormed different strategies to implement persistence. At the end we weighed pros and cons, as well as our strong suits, we came to the conclusion that XML would be the best option for us.");
        out.println("</p>");
        out.println("<p>We then split up the work so that Long added a save button and catch and display the data, and Faiz added functionality to allow the user to reroute that data to generate a truth table.");
        out.println("</p>");
        out.println("<p>We had a difficult time adding to the existent page, but we both learned a lot, splicing code in and adjusting it for our specification, as well as repeatedly deploying the web app and using the errors and generated page as guidance. In our implementation, we also got a lot of practice with XMLEvents as well as routing data using Entry objects.");
        out.println("</p>");
        out.println("</body>");
    }

    private void PrintTail (PrintWriter out){
        out.println("");
        out.println("</html>");
    } 
}