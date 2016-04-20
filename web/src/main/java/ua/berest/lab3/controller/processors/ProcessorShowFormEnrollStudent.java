package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorShowFormEnrollStudent extends Processor {
    public ProcessorShowFormEnrollStudent() {
        actionToPerform = "showFormEnrollStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = OracleDataAccess.getInstance().getCourseById(courseId);
        request.getSession().setAttribute("course", course);
        List<Student> listOfAllStudents = OracleDataAccess.getInstance().getAllStudents();
        request.getSession().setAttribute("listOfAllStudents", listOfAllStudents);
        return new ProcessorResult("pages/template.jsp", "showFormEnrollStudent.jsp", true);
    }
}
