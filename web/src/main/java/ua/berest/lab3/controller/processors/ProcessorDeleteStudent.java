package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 03.03.2016.
 */
public class ProcessorDeleteStudent extends Processor {
    public  ProcessorDeleteStudent() {
        actionToPerform = "deleteStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {

        String[] students = request.getParameterValues("students");
        if (students != null) {
            for (int i = 0; i < students.length; i++)
            {
                OracleDataAccess.getInstance().removeStudent(Integer.parseInt(students[i]));
            }
        }

        return new ProcessorResult("?action=showAllStudents", "showAllStudents.jsp", false);
    }
}
