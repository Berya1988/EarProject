package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorDeleteLocation extends Processor {
    public ProcessorDeleteLocation() {
        actionToPerform = "deleteLocation";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String[] locations = request.getParameterValues("locations");
        Location location = OracleDataAccess.getInstance().getLocationById(Integer.parseInt(locations[0]));
        if (locations != null) {
            for (int i = 0; i < locations.length; i++)
            {
                OracleDataAccess.getInstance().removeLocation(Integer.parseInt(locations[i]));
            }
        }
        return new ProcessorResult(("?action=showAllLocations&parentId=" + location.getParentId()), "showAllLocations.jsp", false);
    }
}
