package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Grade;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorShowFormEditGrade extends Processor {
    public ProcessorShowFormEditGrade() {
        actionToPerform = "showFormEditGrade";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String[] grades = request.getParameterValues("grades");
        Grade grade = OracleDataAccess.getInstance().getGradeById(Integer.parseInt(grades[0]));
        request.getSession().setAttribute("grade", grade);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditGrade.jsp", true);
    }
}