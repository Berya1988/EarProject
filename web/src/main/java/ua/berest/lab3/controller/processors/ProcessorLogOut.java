package ua.berest.lab3.controller.processors;

import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorLogOut extends Processor {
    public ProcessorLogOut() {
        actionToPerform = "logOut";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        request.getSession().invalidate();
        return new ProcessorResult("/", null, true);
    }
}