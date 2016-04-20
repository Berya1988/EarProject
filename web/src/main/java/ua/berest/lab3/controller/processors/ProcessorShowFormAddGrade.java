package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorShowFormAddGrade extends Processor {
    public ProcessorShowFormAddGrade() {
        actionToPerform = "showFormAddGrade";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        request.getSession().setAttribute("grade", null);

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = OracleDataAccess.getInstance().getStudentById(studentId);
        request.getSession().setAttribute("student", student);

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = OracleDataAccess.getInstance().getCourseById(courseId);
        request.getSession().setAttribute("course", course);


        return new ProcessorResult("pages/template.jsp", "showFormAddEditGrade.jsp", true);
    }
}
