package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.domain.event.EventType;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private long id;
    private long userId;
    private String type;
    private Date creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
