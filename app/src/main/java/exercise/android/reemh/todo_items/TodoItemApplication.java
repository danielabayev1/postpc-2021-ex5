package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class TodoItemApplication extends Application {
    private TodoItemsHolderImpl todoItemsHolder;
    private SharedPreferences sp;
    private static TodoItemApplication instance;

    public TodoItemsHolderImpl getTodoItemsHolder() {
        return todoItemsHolder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.sp = this.getSharedPreferences("local_db", Context.MODE_PRIVATE);
        todoItemsHolder = new TodoItemsHolderImpl(this, sp);
    }
    public static TodoItemApplication getInstance(){
        return instance;
    }


}
