package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemHolder> {
    List<TodoItem> _todoItemsList = new ArrayList<>();
    ListLogicOnClickListener onClickDeleteButtonListener = null;
    ListLogicOnClickListener onClickCheckBoxListener = null;

    @Override
    public TodoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);
        return new TodoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoItemHolder holder, int position) {
        TodoItem todoItem = _todoItemsList.get(position);
        //fresh start
        holder.description.setPaintFlags(holder.description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        holder.description.setText(todoItem.getDescription());
        //init the views according the TodoItem status
        boolean status = todoItem.getStatus();
        holder.checkBox.setChecked(status);
        if (status) {
            holder.description.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.deleteButton.setOnClickListener(v -> {
            if (onClickDeleteButtonListener != null) {
                onClickDeleteButtonListener.onClick(_todoItemsList.get(position));
            }
        });
        holder.checkBox.setOnClickListener(v -> {
            if (onClickCheckBoxListener != null) {
                if (!holder.description.getPaint().isStrikeThruText()) {
                    holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.description.setPaintFlags(holder.description.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
                holder.checkBox.setChecked(!todoItem.getStatus());
                onClickCheckBoxListener.onClick(_todoItemsList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this._todoItemsList.size();
    }

    public void setTodo(List<TodoItem> todoItemsList) {
        /* when our todoItemList is changed(added/deleted) we should say that we want to update the items
         * we've rendered before*/
        this._todoItemsList.clear();
        this._todoItemsList.addAll(todoItemsList);
        Collections.sort(this._todoItemsList, Collections.reverseOrder());
        notifyDataSetChanged();
    }
}
