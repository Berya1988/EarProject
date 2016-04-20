package ua.berest.lab3.model;

/**
 * Created by Oleg on 26.01.2016.
 */
public interface Location {
    int getLocationId();
    void setLocationId(int locationId);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    int getParentId();
    void setParentId(int parentId);
    boolean getCourse();
    void setCourse(boolean course);
}
