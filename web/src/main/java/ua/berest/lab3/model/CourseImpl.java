package ua.berest.lab3.model;

/**
 * Created by Oleg on 24.01.2016.
 */

public class CourseImpl implements Course {
    private int courseId;
    private String name;
    private String description;
    private String teacher;
    private int locationId;

    public CourseImpl(int courseId, int locationId, String name, String description, String teacher) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.locationId = locationId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
        CourseImpl other = (CourseImpl) obj;
        return !(locationId != other.locationId || courseId != other.courseId || name != other.name || !description.equals(other.description) || !teacher.equals(other.teacher));
    }

    @Override
    protected Object clone() {
        try{
            CourseImpl copy = (CourseImpl)super.clone();
            return copy;
        }
        catch(CloneNotSupportedException e){
            throw new AssertionError("Impossible");
        }
    }

    @Override
    public String toString() {
        return "Course id = " + courseId + ", name = " + name + ", description = " + description + ", teacher = " + teacher + ", location id = " + locationId + ".";
    }
}
