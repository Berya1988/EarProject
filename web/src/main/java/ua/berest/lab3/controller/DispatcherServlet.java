package ua.berest.lab3.controller;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

import org.xml.sax.SAXException;
import ua.berest.lab3.controller.processors.Processor;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oleg on 18.02.2016.
 */
public class DispatcherServlet extends HttpServlet {

    static final Logger logger = Logger.getLogger(DispatcherServlet.class);
    private  List<Processor> listOfAllProcessors = new ArrayList<Processor>();
    private List<String> namesOfAllProcessors = new ArrayList<String>();
    static {
        logger.info("Your session begin at: " + new Date());
    }

    @Override
    public void init() throws ServletException {
        try {
            extractProcessorNamesFromXMLFile("/WEB-INF/resources/processors.xml");
            logger.info(namesOfAllProcessors.size() + " processors were initialized.");
        } catch (IOException e) {
            logger.error("Threw a IOException in DispatcherServlet class::" + e.getMessage(), e);
            throw new ServletException(e);
        }

        for (String name : namesOfAllProcessors) {
            try {
                listOfAllProcessors.add((Processor) Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                logger.error("Threw a InstantiationException in DispatcherServlet class::" + e.getMessage(), e);
                throw new ServletException(e);
            } catch (IllegalAccessException e) {
                logger.error("Threw a IllegalAccessException in DispatcherServlet class::" + e.getMessage(), e);
                throw new ServletException(e);
            } catch (ClassNotFoundException e) {
                logger.error("Threw a ClassNotFoundException in DispatcherServlet class::" + e.getMessage(), e);
                throw new ServletException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("Current value of action parameter: " + request.getParameter("action"));
        for (Processor processor : listOfAllProcessors) {
            try {
                if(processor.canProcess(request.getParameter("action"))){
                    ProcessorResult result = processor.process(request);
                    if(result.getIncludedPage() != null) {
                        request.getSession().setAttribute("includedJSPName", result.getIncludedPage());
                    }
                    if (result.isForward()) {
                        try {
                            RequestDispatcher rd = request.getRequestDispatcher(result.getUrl());
                            rd.forward(request, response);
                        } catch (ServletException e) {
                            logger.error("Threw a ServletException in DispatcherServlet class::" + e.getMessage(), e);
                        }
                    }
                    else {
                        response.sendRedirect("DispatcherServlet" + result.getUrl());
                    }
                    break;
                }
            } catch (DataAccessException e) {
                logger.error("Threw a DataAccessException in DispatcherServlet class::" + e.getMessage(), e);
            }
        }
    }

    private void extractProcessorNamesFromXMLFile(String path) throws IOException {

        String fullPath = getServletContext().getResource(path).getPath();
        File xmlFile = new File(fullPath);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Threw a ParserConfigurationException in DispatcherServlet class::" + e.getMessage(), e);
            throw new IOException(e);
        }

        try {
            Document document = docBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList list = document.getElementsByTagName("processor");
            String className;
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if(node instanceof Element) {
                    Element element = (Element)list.item(i);
                    className = element.getElementsByTagName("processorName").item(0).getTextContent();
                    namesOfAllProcessors.add(className);
                }
            }
        } catch (SAXException e) {
            logger.error("Threw a SAXException in DispatcherServlet class::" + e.getMessage(), e);
            throw new IOException(e);
        }
    }
}
