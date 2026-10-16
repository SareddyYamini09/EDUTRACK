package model;

/**
 * Represents a physical or virtual campus resource (classroom, lab, server, etc.).
 */
public class Resource {
    private String resourceId;
    private String resourceName;
    private String resourceType;
    private String location;
    private int capacity;
    private boolean availability;

    public Resource(String resourceId, String resourceName, String resourceType, String location, int capacity, boolean availability) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.location = location;
        this.capacity = capacity;
        this.availability = availability;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return String.format("Resource[ID=%-8s, Name=%-25s, Type=%-15s, Loc=%-12s, Cap=%3d, Available=%s]",
                resourceId, resourceName, resourceType, location, capacity, availability ? "YES" : "NO");
    }
}
