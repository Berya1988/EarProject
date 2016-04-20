package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorShowFormEditCourse extends Processor {
    public ProcessorShowFormEditCourse() {
        actionToPerform = "showFormEditCourse";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String[] courses = request.getParameterValues("courses");
        Course course = OracleDataAccess.getInstance().getCourseById(Integer.parseInt(courses[0]));
        request.getSession().setAttribute("course", course);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditCourse.jsp", true);
    }
}