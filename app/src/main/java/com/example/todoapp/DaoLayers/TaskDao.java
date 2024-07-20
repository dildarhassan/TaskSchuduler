package com.example.todoapp.DaoLayers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(TaskEntity task);

    @Update
    void update(TaskEntity task);

    @Delete
    void delete(TaskEntity task);

    @Query("SELECT * FROM tasks")
    List<TaskEntity> getAllTasks();
}
