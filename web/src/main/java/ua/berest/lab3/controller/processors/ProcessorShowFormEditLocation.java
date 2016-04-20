package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorShowFormEditLocation extends Processor {
    public ProcessorShowFormEditLocation() {
        actionToPerform = "showFormEditLocation";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        request.getSession().setAttribute("parentId", request.getParameter("parentId"));
        String[] locations = request.getParameterValues("locations");
        Location location = OracleDataAccess.getInstance().getLocationById(Integer.parseInt(locations[0]));
        request.getSession().setAttribute("location", location);
        Map<Integer, String> mapOfAllLocations = OracleDataAccess.getInstance().getAllLocations();
        mapOfAllLocations.put(0, "Країни");
        request.getSession().setAttribute("mapOfAllLocations", mapOfAllLocations);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditLocation.jsp", true);
    }
}