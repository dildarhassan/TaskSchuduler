package com.example.todoapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskEntity> tasks = new ArrayList<>();
    private OnTaskClickListener listener;

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TaskEntity task = tasks.get(position);
        holder.textViewTaskTitle.setText(task.getTitle());
        holder.textViewTaskDueDate.setText(task.getDueDate());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onTaskClick(task);
            }
        });

        holder.ivDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onTaskDelete(task);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public interface OnTaskClickListener {
        void onTaskClick(TaskEntity task);
        void onTaskDelete(TaskEntity task);
    }

    public void setOnTaskClickListener(OnTaskClickListener listener) {
        this.listener = listener;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTaskTitle;
        TextView textViewTaskDueDate;
        ImageView ivDeleteTask,ivEditTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTaskTitle);
            textViewTaskDueDate = itemView.findViewById(R.id.textViewTaskDueDate);
            ivDeleteTask = itemView.findViewById(R.id.imageViewDelete);
            ivEditTask = itemView.findViewById(R.id.imageViewEdit);

        }
    }
}
