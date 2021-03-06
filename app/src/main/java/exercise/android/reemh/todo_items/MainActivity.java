package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public TodoItemsHolder holder = null;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (holder == null) {
            holder = TodoItemApplication.getInstance().getTodoItemsHolder();
        }

        TodoItemAdapter adapter = new TodoItemAdapter();


        //find Views
        FloatingActionButton fab = findViewById(R.id.buttonCreateTodoItem);
        this.editText = findViewById(R.id.editTextInsertTask);
        RecyclerView rv = findViewById(R.id.recyclerTodoItemsList);
        adapter.setTodo(this.holder.getCurrentItems());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //onRestore
        if (savedInstanceState != null) {
            this.holder = TodoItemApplication.getInstance().getTodoItemsHolder();
            adapter.setTodo(this.holder.getCurrentItems());
            this.editText.setText(savedInstanceState.getString("text"));
        }
        this.holder.getLiveData().observe(this, new Observer<List<TodoItem>>() {
            @Override
            public void onChanged(List<TodoItem> todoItems) {
                adapter.setTodo(todoItems);
            }
        });


        // TODO: implement the specs as defined below
        //    (find all UI components, hook them up, connect everything you need)


        fab.setOnClickListener(v -> {
            String input = this.editText.getText().toString();
            if (!(input.equals(""))) {
                this.holder.addNewInProgressItem(input);
                adapter.setTodo(this.holder.getCurrentItems());
                this.editText.setText("");
            }
        });
        adapter.onClickDeleteButtonListener = todoItem -> {
            this.holder.deleteItem(todoItem);
            adapter.setTodo(this.holder.getCurrentItems());
        };
        adapter.onClickCheckBoxListener = todoItem -> {
            if (todoItem.getStatus()) {
                this.holder.markItemInProgress(todoItem);
            } else {
                this.holder.markItemDone(todoItem);
            }
            adapter.setTodo(this.holder.getCurrentItems());
        };
        adapter.onClickEditListener = todoItem -> {
            Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
            int i;
            List<TodoItem> todoItemList = holder.getCurrentItems();
            for (i = 0; i < todoItemList.size(); i++) {
                if (todoItemList.get(i).getDescription().equals(todoItem.getDescription())) {
                    break;
                }

            }
            editIntent.putExtra("position", i);
            startActivity(editIntent);
        };

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", this.editText.getText().toString());
    }
}

/*

SPECS:

- the screen starts out empty (no items shown, edit-text input should be empty)
- every time the user taps the "add TODO item" button:
    * if the edit-text is empty (no input), nothing happens
    * if there is input:
        - a new TodoItem (checkbox not checked) will be created and added to the items list
        - the new TodoItem will be shown as the first item in the Recycler view
        - the edit-text input will be erased
- the "TodoItems" list is shown in the screen
  * every operation that creates/edits/deletes a TodoItem should immediately be shown in the UI
  * the order of the TodoItems in the UI is:
    - all IN-PROGRESS items are shown first. items are sorted by creation time,
      where the last-created item is the first item in the list
    - all DONE items are shown afterwards, no particular sort is needed (but try to think about what's the best UX for the user)
  * every item shows a checkbox and a description. you can decide to show other data as well (creation time, etc)
  * DONE items should show the checkbox as checked, and the description with a strike-through text
  * IN-PROGRESS items should show the checkbox as not checked, and the description text normal
  * upon click on the checkbox, flip the TodoItem's state (if was DONE will be IN-PROGRESS, and vice versa)
  * add a functionality to remove a TodoItem. either by a button, long-click or any other UX as you want
- when a screen rotation happens (user flips the screen):
  * the UI should still show the same list of TodoItems
  * the edit-text should store the same user-input (don't erase input upon screen change)

Remarks:
- you should use the `holder` field of the activity
- you will need to create a class extending from RecyclerView.Adapter and use it in this activity
- notice that you have the "row_todo_item.xml" file and you can use it in the adapter
- you should add tests to make sure your activity works as expected. take a look at file `MainActivityTest.java`



(optional, for advanced students:
- save the TodoItems list to file, so the list will still be in the same state even when app is killed and re-launched
)

*/
