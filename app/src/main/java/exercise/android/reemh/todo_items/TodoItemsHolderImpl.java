package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder, Serializable {
    public List<TodoItem> todoItemList = new ArrayList();

    @Override
    public List<TodoItem> getCurrentItems() {
        return new ArrayList<>(this.todoItemList);
    }

    @Override
    public void addNewInProgressItem(String description) {
        this.todoItemList.add( new TodoItem(description, false, System.currentTimeMillis()));
    }

    @Override
    public void markItemDone(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setDone();
                break;
            }
        }
//        Collections.sort(this.todoItemList);
    }

    @Override
    public void markItemInProgress(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setInProgress();
                break;
            }
        }
//        Collections.sort(this.todoItemList);
    }

    @Override
    public void deleteItem(TodoItem item) {
        this.todoItemList.remove(item);
    }
}
