package com.example.demo.models.Enum;

public enum VideoEnumStatus {
    ACTIVE(true), PROGRESS(false);


    private final boolean active;
    VideoEnumStatus(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

}
