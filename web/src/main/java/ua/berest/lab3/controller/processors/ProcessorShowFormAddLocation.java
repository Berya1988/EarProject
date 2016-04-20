package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorShowFormAddLocation extends Processor {
    public ProcessorShowFormAddLocation() {
        actionToPerform = "showFormAddLocation";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        request.getSession().setAttribute("parentId", request.getParameter("parentId"));
        request.getSession().setAttribute("location", null);
        Map<Integer, String> mapOfAllLocations = OracleDataAccess.getInstance().getAllLocations();
        mapOfAllLocations.put(0, "Країни");
        request.getSession().setAttribute("mapOfAllLocations", mapOfAllLocations);
        return new ProcessorResult("pages/template.jsp", "showFormAddEditLocation.jsp", true);
    }
}
