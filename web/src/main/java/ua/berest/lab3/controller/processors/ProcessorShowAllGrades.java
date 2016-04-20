package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.Grade;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleg on 18.02.2016.
 */
public class ProcessorShowAllGrades extends Processor {
    public ProcessorShowAllGrades() {
        actionToPerform = "showAllGrades";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        Student student = OracleDataAccess.getInstance().getStudentById(studentId);
        request.getSession().setAttribute("student", student);
        Course course = OracleDataAccess.getInstance().getCourseById(courseId);
        request.getSession().setAttribute("course", course);

        List<Grade> listOfAllGrades = OracleDataAccess.getInstance().getAllGradesByCourseAndStudent(courseId,studentId);
        request.getSession().setAttribute("listOfAllGrades", listOfAllGrades);
        return new ProcessorResult("pages/template.jsp", "showAllGrades.jsp", true);
    }
}
