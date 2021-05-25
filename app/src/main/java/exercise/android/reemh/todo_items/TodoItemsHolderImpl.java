package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder {
    public List<TodoItem> todoItemList = new ArrayList();
    private final Context context;
    private final SharedPreferences sp;
    private Gson gson;

    private MutableLiveData<List<TodoItem>> toDoItemsLiveDataMutable;

    public LiveData<List<TodoItem>> toDoItemsLiveData;

    public TodoItemsHolderImpl(Context context, SharedPreferences sp) {
        this.toDoItemsLiveDataMutable = new MutableLiveData<>();
        this.toDoItemsLiveData = toDoItemsLiveDataMutable;
        this.context = context;
        this.sp = sp;
        this.gson = new Gson();
        initializeFromSp();
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
    }

    public LiveData<List<TodoItem>> getLiveData(){
        return this.toDoItemsLiveData;
    }

    private void initializeFromSp() {
        Set<String> keys = this.sp.getAll().keySet();
        for (String key : keys) {
            String todoItem = sp.getString(key, null);
            TodoItem todoItem1 = gson.fromJson(todoItem, TodoItem.class);
            todoItemList.add(todoItem1);
        }
        Collections.sort(this.todoItemList, Collections.reverseOrder());
    }


    @Override
    public List<TodoItem> getCurrentItems() {
        return new ArrayList<>(this.todoItemList);
    }

    @Override
    public void addNewInProgressItem(String description) {
        TodoItem newTodoItem = new TodoItem(description, false, System.currentTimeMillis());
        this.todoItemList.add(0, newTodoItem);
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
        manageSp(newTodoItem);
    }

    public void changeDescription(String oldDescription, String newDescription) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(oldDescription)) {
                this.todoItemList.get(i).setDescription(newDescription);
                break;
            }
        }
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
//        Collections.sort(this.todoItemList, Collections.reverseOrder());
    }

    @Override
    public void markItemDone(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setDone();
                break;
            }
        }
        Collections.sort(this.todoItemList, Collections.reverseOrder());
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
        manageSp(item);
    }

    @Override
    public void markItemInProgress(TodoItem item) {
        for (int i = 0; i < this.todoItemList.size(); i++) {
            if (this.todoItemList.get(i).getDescription().equals(item.getDescription())) {
                this.todoItemList.get(i).setInProgress();
                break;
            }
        }

        Collections.sort(this.todoItemList, Collections.reverseOrder());
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
        manageSp(item);
    }

    public void manageSp(TodoItem item) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(item.getTimeStampCreation().toString(), gson.toJson(item));
        editor.apply();
    }

    @Override
    public void deleteItem(TodoItem item) {
        this.todoItemList.remove(item);
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(todoItemList));
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(item.getTimeStampCreation().toString());
        editor.apply();
    }
}
