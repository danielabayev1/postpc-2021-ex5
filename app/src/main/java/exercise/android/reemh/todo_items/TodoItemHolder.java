package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class TodoItemHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView description;
    Button deleteButton;

    public TodoItemHolder(View itemView) {
        super(itemView);
        this.checkBox = itemView.findViewById(R.id.checkBox);
        this.description = itemView.findViewById(R.id.descriptionText);
        this.deleteButton = itemView.findViewById(R.id.deleteItem);
    }
}
