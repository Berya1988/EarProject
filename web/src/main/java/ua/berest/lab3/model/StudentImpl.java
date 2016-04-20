package ua.berest.lab3.model;

/**
 * Created by Oleg on 24.01.2016.
 */
public class StudentImpl implements Student {
    private int studentId;
    private String fio;
    private String group;
    private String mail;
    private String phone;
    private String address;

    public StudentImpl(int studentId, String fio, String group, String mail, String phone, String address) {
        this.studentId = studentId;
        this.fio = fio;
        this.group = group;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentImpl other = (StudentImpl) obj;
        return !(studentId != other.studentId || !fio.equals(other.fio) || !group.equals(other.group) || !mail.equals(other.mail) || !phone.equals(other.phone) || !address.equals(other.address));
    }

    @Override
    protected Object clone() {
        try{
            StudentImpl copy = (StudentImpl) super.clone();
            return copy;
        }
        catch(CloneNotSupportedException e){
            throw new AssertionError("Impossible");
        }
    }

    @Override
    public String toString() {
        return "Sudent id = " + studentId + ", full name = " + fio + ", e-mail = " + mail + ", phone number = " + phone + ", home address = " + address  + ", group = " + group + ".";
    }
}
