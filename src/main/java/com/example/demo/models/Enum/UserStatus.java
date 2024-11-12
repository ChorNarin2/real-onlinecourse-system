package com.example.demo.models.Enum;

public enum UserStatus {
    ACTIVE(true),
    INACTIVE(false), 
    SUSPENDED(false);


    private final boolean isActive;

   
    UserStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive(){
        return isActive;
    }
    
}

