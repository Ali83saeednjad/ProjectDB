package com.example.projectdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String phone;
}

