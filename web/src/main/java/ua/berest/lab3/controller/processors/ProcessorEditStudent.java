package ua.berest.lab3.controller.processors;

import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;
import ua.berest.lab3.model.StudentImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorEditStudent extends Processor {
    public ProcessorEditStudent() {
        actionToPerform = "editStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        int id = Integer.valueOf(request.getParameter("idName"));
        String lastName = request.getParameter("userName");
        String group = request.getParameter("groupName");
        String mail = request.getParameter("mailName");
        String phone = request.getParameter("phoneName");
        String address = request.getParameter("addressName");
        Student student = new StudentImpl(id, lastName, group, mail, phone, address);
        OracleDataAccess.getInstance().updateStudent(student);
        return new ProcessorResult("?action=showAllStudents", "showAllStudents.jsp", false);
    }
}
