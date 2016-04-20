package ua.berest.lab3.model;

/**
 * Created by Oleg on 26.01.2016.
 */
public interface Course {
    int getCourseId();
    void setCourseId(int courseId);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    String getTeacher();
    void setTeacher(String teacher);
    int getLocationId();
    void setLocationId(int locationId);
}
