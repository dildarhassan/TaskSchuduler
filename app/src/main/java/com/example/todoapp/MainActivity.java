package com.example.todoapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Adapter.TaskAdapter;
import com.example.todoapp.entity.TaskEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private TaskDatabase taskDatabase;
    private static final int REQUEST_CODE_ADD_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter();
        recyclerViewTasks.setAdapter(taskAdapter);

        taskDatabase = TaskDatabase.getInstance(this);

        loadTasks();

        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
            }
        });
    }

    private void loadTasks() {

        try {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    List<TaskEntity> tasks = taskDatabase.taskDao().getAllTasks();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            taskAdapter.setTasks(tasks);
                        }
                    });
                }
            });
        }catch (Exception e){

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK && data != null) {
            String taskTitle = data.getStringExtra("taskTitle");
            String taskDueDate = data.getStringExtra("taskDueDate");
            TaskEntity newTask = new TaskEntity(taskTitle, taskDueDate);
            insertTask(newTask);
        }
    }

    private void insertTask(TaskEntity task) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                taskDatabase.taskDao().insert(task);
                loadTasks();
            }
        });
    }
}