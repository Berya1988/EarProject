package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorDeleteCourse extends Processor {
    public ProcessorDeleteCourse() {
        actionToPerform = "deleteCourse";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int location_id = Integer.valueOf(request.getParameter("parentLocation"));
        String[] courses = request.getParameterValues("courses");
        if (courses != null) {
            for (int i = 0; i < courses.length; i++)
            {
                OracleDataAccess.getInstance().removeCourse(Integer.parseInt(courses[i]));
            }
        }
        return new ProcessorResult(("?action=showAllLocations&parentId=" + location_id), "showAllLocations.jsp", false);
    }
}
