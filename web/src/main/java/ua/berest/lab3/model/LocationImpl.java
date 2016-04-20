package ua.berest.lab3.model;

/**
 * Created by Oleg on 24.01.2016.
 */
public class LocationImpl implements Location {
    private int locationId;
    private String name;
    private String description;
    private int parentId;
    private boolean isCourse;

    public LocationImpl(int locationId, String name, int parentId, boolean isCourse, String description) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.isCourse = isCourse;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean getCourse() {
        return isCourse;
    }

    public void setCourse(boolean course) {
        isCourse = course;
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
        LocationImpl other = (LocationImpl) obj;
        return !(locationId != other.locationId || parentId != other.parentId || !name.equals(other.name) || !description.equals(other.description) || isCourse != other.isCourse);
    }

    @Override
    protected Object clone() {
        try{
            LocationImpl copy = (LocationImpl) super.clone();
            return copy;
        }
        catch(CloneNotSupportedException e){
            throw new AssertionError("Impossible");
        }
    }

    @Override
    public String toString() {
        return "Parent id = " + parentId + ", name = " + name + ", description = " + description + ", is course(true or false) = " + isCourse + ", location id = " + locationId + ".";    }
}
