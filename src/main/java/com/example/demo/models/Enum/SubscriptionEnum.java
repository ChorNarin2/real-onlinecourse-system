package com.example.demo.models.Enum;

public enum SubscriptionEnum {
    ACTIVE(true), 
    PROGRESS(false), 
    CANCELED(false), 
    REJECTED(false);

    private final boolean isActive;

    SubscriptionEnum(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

}
