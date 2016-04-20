package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorUnenrollStudent extends Processor {
    public ProcessorUnenrollStudent() {
        actionToPerform = "unenrollStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = OracleDataAccess.getInstance().getCourseById(courseId);
        String[] students = request.getParameterValues("students");
        if (students != null) {
            for (int i = 0; i < students.length; i++)
            {
                OracleDataAccess.getInstance().removeEnrollment(Integer.parseInt(students[i]), courseId);
            }
        }
        return new ProcessorResult(("?action=showAllStudentsInCourse&courseId=" + course.getCourseId() + "&locationId="+ course.getLocationId()), "showAllLocations.jsp", false);

    }
}
