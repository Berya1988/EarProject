package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorAddGrade extends Processor {
    public ProcessorAddGrade() {
        actionToPerform = "addGrade";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {

        int courseId = Integer.valueOf(request.getParameter("courseId"));
        int studentId = Integer.valueOf(request.getParameter("studentId"));
        int currentGrade = Integer.valueOf(request.getParameter("currentGrade"));

        Date date = new Date(System.currentTimeMillis());
        String description = request.getParameter("descriptionName");


        Grade grade = new GradeImpl(1, studentId, courseId, date, currentGrade, description);
        OracleDataAccess.getInstance().addGrade(grade);
        return new ProcessorResult(("?action=showAllGrades&courseId=" + courseId + "&studentId=" + studentId), "showAllGrades.jsp", false);
    }
}
