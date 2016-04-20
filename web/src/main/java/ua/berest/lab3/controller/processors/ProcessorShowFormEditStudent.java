package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorShowFormEditStudent extends Processor {
    public ProcessorShowFormEditStudent() {
        actionToPerform = "showFormEditStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String[] students = request.getParameterValues("students");
        Student student = OracleDataAccess.getInstance().getStudentById(Integer.parseInt(students[0]));
        request.getSession().setAttribute("student", student);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditStudent.jsp", true);
    }
}