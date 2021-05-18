package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.List;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder {
    public List<TodoItem> todoItemList = new ArrayList();

    @Override
    public List<TodoItem> getCurrentItems(){
        return new ArrayList<>(this.todoItemList);
    }

    @Override
    public void addNewInProgressItem(String description) {
        this.todoItemList.add(new TodoItem(description, false));
    }

    @Override
    public void markItemDone(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setDone();
                break;
            }
        }
    }

    @Override
    public void markItemInProgress(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setInProgress();
                break;
            }
        }
    }

    @Override
    public void deleteItem(TodoItem item) {
        this.todoItemList.remove(item);
    }
}
