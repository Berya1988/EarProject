package ua.berest.lab3.model;

/**
 * Created by Oleg on 26.01.2016.
 */
public interface Student {
    int getStudentId();
    void setStudentId(int studentId);
    String getFio();
    void setFio(String fio);
    String getGroup();
    void setGroup(String group);
    String getMail();
    void setMail(String mail);
    String getPhone();
    void setPhone(String phone);
    String getAddress();
    void setAddress(String address);
}
