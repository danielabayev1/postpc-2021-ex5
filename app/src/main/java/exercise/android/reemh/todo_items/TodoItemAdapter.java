package exercise.android.reemh.todo_items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemHolder> {
    List<TodoItem> todoItemsList;

    @Override
    public TodoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);
        return new TodoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoItemHolder holder, int position) {
        TodoItem todoItem = todoItemsList.get(position);
        holder.description.setText(todoItem.getDescription());
        holder.checkBox.setChecked(todoItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return this.todoItemsList.size();
    }

    public void setTodo(TodoItemsHolder todoList) {
        /* when our todoItemList is changed(added/deleted) we should say that we want to update the items
         * we've rendered before*/
        todoItemsList.clear();
        this.todoItemsList = todoList.getCurrentItems();
        notifyDataSetChanged();
    }
}
