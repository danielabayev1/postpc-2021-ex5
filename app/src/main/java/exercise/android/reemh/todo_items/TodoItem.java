package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    // TODO: edit this class as you want
    private String description;
    private String status;

    public TodoItem(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }


}
