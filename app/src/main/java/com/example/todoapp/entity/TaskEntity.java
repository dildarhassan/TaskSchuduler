package com.example.todoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")

public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String dueDate;

    public TaskEntity(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

}
