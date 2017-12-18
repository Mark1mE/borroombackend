package com.borroom.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boardroom")
public class Boardroom {
    @Id
    private String roomnum;
    private Integer maxcap;
    private Boolean projector;
    private Boolean available;

    public Boardroom() {
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getMaxcap() {
        return maxcap;
    }

    public void setMaxcap(Integer maxcap) {
        this.maxcap = maxcap;
    }

    public Boolean getProjector() {
        return projector;
    }

    public void setProjector(Boolean projector) {
        this.projector = projector;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
