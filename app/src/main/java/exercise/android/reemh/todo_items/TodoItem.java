package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable, Comparable<TodoItem> {
    // TODO: edit this class as you want
    private String description;
    private boolean isDone;
    private Long timeStamp;

    public TodoItem(String description, boolean isDone, Long timeStamp) {
        this.description = description;
        this.isDone = isDone;
        this.timeStamp = timeStamp;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setInProgress() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }


    @Override
    public int compareTo(TodoItem o) {
        if (!this.isDone && o.isDone) {
            return 1;
        } else if ((!this.isDone && !o.isDone) ||(this.isDone && o.isDone)) {
            return Long.compare(this.timeStamp,o.timeStamp);
        } else{
            return -1;
        }

    }
}
