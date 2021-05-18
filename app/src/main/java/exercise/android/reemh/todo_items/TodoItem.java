package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    // TODO: edit this class as you want
    private String description;
    private boolean done;

    public TodoItem(String description, boolean status) {
        this.description = description;
        this.done = status;
    }

    public void setDone() {
        this.done = true;
    }

    public void setInProgress() {
        this.done = false;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getStatus(){return this.done;}


}
