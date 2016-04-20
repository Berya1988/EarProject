package ua.berest.lab3.controller.processors;

import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorShowFormAddStudent extends Processor {
    public ProcessorShowFormAddStudent() {
        actionToPerform = "showFormAddStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        request.getSession().setAttribute("student", null);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditStudent.jsp", true);
    }
}
