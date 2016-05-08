package ua.berest.lab3.controller.processors;

import org.apache.log4j.Logger;
import ua.berest.lab3.controller.OracleDataAccess;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.ProcessorResult;
import ua.berest.lab3.model.Student;
import ua.berest.lab3.model.StudentImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 02.03.2016.
 */
public class ProcessorAddStudent extends Processor {
    static final Logger logger = Logger.getLogger(ProcessorAddStudent.class);
    public ProcessorAddStudent() {
        actionToPerform = "addStudent";
    }
    public ProcessorResult process(HttpServletRequest request) throws DataAccessException {
        String lastName = request.getParameter("userName");
        String group = request.getParameter("groupName");
        String mail = request.getParameter("mailName");
        String phone = request.getParameter("phoneName");
        String address = request.getParameter("addressName");
        Student student = new StudentImpl(1, lastName, group, mail, phone, address);
        logger.info("FIO: " + student.getFio() + "Group: " + student.getGroup() + "Mail: " + student.getMail() + "Phone: " + student.getPhone() + "Address: " + student.getAddress());
        OracleDataAccess.getInstance().addStudent(student);
        return new ProcessorResult("?action=showAllStudents&page=1", "showAllStudents.jsp", false);
    }
}
