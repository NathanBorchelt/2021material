package com.example.triptracker2021;

import java.util.Date;

public class Trip implements IntentData{

    private String objectId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean shared;
    private String creator;

    public Trip(String objectId, String name, String description, Date startDate, Date endDate, boolean shared,String creator) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        this.startDate = endDate;
        this.endDate = startDate;
        this.shared = shared;
        this.creator = creator;
    }

    public String getCreator() {return creator;}
    public Date getEndDate() {return endDate;}
    public Date getStartDate() {return startDate;}
    public String getDescription() {return description;}
    public String getName() {return name;}
    public String getObjectId() {return objectId;}
    public boolean isShared() {return shared;}
    public void setDescription(String description) {this.description = description;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}
    public void setName(String name) {this.name = name;}
    public void setObjectId(String objectId) {this.objectId = objectId;}
    public void setShared(boolean shared) {this.shared = shared;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}
    public void setCreator(String creator) {this.creator = creator;}

    @Override
    public String toString() {
        return "Trip{" +
                "objectId='" + objectId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", shared=" + shared +
                ", creator="+creator+
                '}';
    }

    public int compareTo(Object o) {
        //compareTo is an Object.class method that we basically recreate
        return name.compareTo(((Trip)o).name);
    }
}
