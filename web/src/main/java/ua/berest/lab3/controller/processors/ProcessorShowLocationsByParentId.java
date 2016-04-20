package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleg on 11.03.2016.
 */
public class ProcessorShowLocationsByParentId extends Processor {
    public ProcessorShowLocationsByParentId() {
        actionToPerform = "showAllLocations";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        Map<Integer, String> mapOfAllLocations = OracleDataAccess.getInstance().getLocationHierarchy(parentId);
        request.getSession().setAttribute("mapOfAllLocationsById", mapOfAllLocations);
        Location location = OracleDataAccess.getInstance().getLocationById(parentId);
        if(location != null && location.getCourse() == true){
            List<Course> listOfCourses = OracleDataAccess.getInstance().getCoursesByLocationId(parentId);
            request.getSession().setAttribute("listOfCourses", listOfCourses);
            request.getSession().setAttribute("parentLocation", location);
            return new ProcessorResult("pages/template.jsp", "showCourses.jsp", true);
        }
        else {
            List<Location> listOfAllLocations = OracleDataAccess.getInstance().getAllLocationsByParentId(parentId);
            request.getSession().setAttribute("listOfAllLocations", listOfAllLocations);
            return new ProcessorResult("pages/template.jsp", "showAllLocations.jsp", true);
        }
    }
}
