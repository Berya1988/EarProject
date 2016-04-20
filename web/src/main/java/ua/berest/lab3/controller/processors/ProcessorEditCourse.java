package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorEditCourse extends Processor {
    public ProcessorEditCourse() {
        actionToPerform = "editCourse";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int id = Integer.valueOf(request.getParameter("idName"));
        int location_id = Integer.valueOf(request.getParameter("location_id"));
        String lastName = request.getParameter("courseName");
        String description = request.getParameter("courseDescription");
        String teacher = request.getParameter("teacherName");

        Course course = new CourseImpl(id, location_id, lastName, description, teacher);

        OracleDataAccess.getInstance().updateCourse(course);
        return new ProcessorResult(("?action=showAllLocations&parentId=" + location_id), "showAllLocations.jsp", false);
    }
}
