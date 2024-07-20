package com.example.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private EditText editTextTaskDueDate;
    private Button buttonSaveTask;
    private int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        editTextTaskDueDate = findViewById(R.id.editTextTaskDueDate);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);

        Intent intent = getIntent();
        if (intent.hasExtra("taskId")) {
            setTitle("Edit Task");
            taskId = intent.getIntExtra("taskId", -1);
            editTextTaskTitle.setText(intent.getStringExtra("taskTitle"));
            editTextTaskDueDate.setText(intent.getStringExtra("taskDueDate"));
        } else {
            setTitle("Add Task");
        }

        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle = editTextTaskTitle.getText().toString();
                String taskDueDate = editTextTaskDueDate.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("taskId", taskId);
                resultIntent.putExtra("taskTitle", taskTitle);
                resultIntent.putExtra("taskDueDate", taskDueDate);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
