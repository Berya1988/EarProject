package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleg on 08.03.2016.
 */
public class ProcessorShowStudentCourses extends Processor {
    public ProcessorShowStudentCourses() {
        actionToPerform = "showStudentCourses";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = OracleDataAccess.getInstance().getStudentById(studentId);
        request.getSession().setAttribute("student", student);
        List<Course> listOfStudentCourses = OracleDataAccess.getInstance().getCoursesByStudentId(studentId);
        request.getSession().setAttribute("listOfStudentCourses", listOfStudentCourses);
        return new ProcessorResult("pages/template.jsp", "showStudentCourses.jsp", true);
    }
}

