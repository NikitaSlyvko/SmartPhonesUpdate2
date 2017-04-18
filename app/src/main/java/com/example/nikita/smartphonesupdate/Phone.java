package com.example.nikita.smartphonesupdate;

import java.util.UUID;

public class Phone {
    private String[] description;
    private int drawablePhoto;
    private UUID id;
    private String phoneName;

    public Phone() {
        this.id = UUID.randomUUID();
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public void setDrawablePhoto(int drawablePhoto) {
        this.drawablePhoto = drawablePhoto;
    }

    public UUID getId() {
        return this.id;
    }

    public String getPhoneName() {
        return this.phoneName;
    }

    public String[] getDescription() {
        return this.description;
    }

    public int getDrawablePhoto() {
        return this.drawablePhoto;
    }
}
