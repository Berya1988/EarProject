package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorDeleteGrade extends Processor {
    public ProcessorDeleteGrade() {
        actionToPerform = "deleteGrade";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        int studentId = Integer.valueOf(request.getParameter("studentId"));
        String[] grades = request.getParameterValues("grades");
        if (grades != null) {
            for (int i = 0; i < grades.length; i++)
            {
                OracleDataAccess.getInstance().removeGrade(Integer.parseInt(grades[i]));
            }
        }
        return new ProcessorResult(("?action=showAllGrades&courseId=" + courseId + "&studentId=" + studentId), "showAllGrades.jsp", false);
    }
}
