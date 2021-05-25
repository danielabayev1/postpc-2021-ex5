package exercise.android.reemh.todo_items;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private TodoItemsHolderImpl todoItemsHolder;
    CheckBox checkBox;
    EditText descriptionView;
    TextView creationTimeText;
    TextView modifiedTimeText;
    String oldDescription;
    int position;
    TodoItem todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent createdMe = getIntent();

        //find Views
        checkBox = findViewById(R.id.editCheckBox);
        descriptionView = findViewById(R.id.editDescriptionText);
        creationTimeText = findViewById(R.id.creationTime);
        modifiedTimeText = findViewById(R.id.modifiedTime);

        this.todoItemsHolder = TodoItemApplication.getInstance().getTodoItemsHolder();


        // get item data
//        int creationTime = createdMe.getStringExtra("creation_time");
//        String modifiedTime = createdMe.getStringExtra("modified_time");

//        boolean status = createdMe.getBooleanExtra("status", false);
        position = createdMe.getIntExtra("position", 0);
        this.todoItem=todoItemsHolder.getCurrentItems().get(position);
        oldDescription = todoItem.getDescription();

        //set views
        checkBox.setChecked(todoItem.getStatus());
        creationTimeText.setText(todoItem.getCreationTimeString());
        modifiedTimeText.setText(todoItem.getLastTimeChanges());
        descriptionView.setText(todoItem.getDescription());

        descriptionView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                todoItemsHolder.changeDescription(oldDescription, descriptionView.getText().toString());
                modifiedTimeText.setText(todoItem.getLastTimeChanges());
                oldDescription = descriptionView.getText().toString();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    todoItemsHolder.markItemDone(todoItemsHolder.getCurrentItems().get(position));
                } else {
                    todoItemsHolder.markItemInProgress(todoItemsHolder.getCurrentItems().get(position));
                }
                modifiedTimeText.setText(todoItem.getLastTimeChanges());
            }
        });

    }

}