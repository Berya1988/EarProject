package ua.berest.lab3.controller.processors;

import org.apache.log4j.Logger;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorMainPage extends Processor {
    static final Logger logger = Logger.getLogger(Processor.class);
    public ProcessorMainPage() {
        actionToPerform = "mainPage";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String username = request.getParameter("username");
        logger.info("User name: " + username);
        request.getSession().setAttribute("username", username);
        return new ProcessorResult("pages/template.jsp", null, true);
    }
}
