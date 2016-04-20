package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleg on 18.02.2016.
 */
public class ProcessorShowAllStudentsInCourse extends Processor {
    public ProcessorShowAllStudentsInCourse() {
        actionToPerform = "showAllStudentsInCourse";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = OracleDataAccess.getInstance().getLocationById(locationId);
        Course course = OracleDataAccess.getInstance().getCourseById(courseId);
        request.getSession().setAttribute("location", location);
        request.getSession().setAttribute("course", course);
        List<Student> listOfAllStudentsInCourse = OracleDataAccess.getInstance().getAllStudentsInCourse(courseId);
        request.getSession().setAttribute("listOfAllStudentsInCourse", listOfAllStudentsInCourse);
        return new ProcessorResult("pages/template.jsp", "showAllStudentsInCourse.jsp", true);
    }
}
