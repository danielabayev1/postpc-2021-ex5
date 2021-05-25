package exercise.android.reemh.todo_items;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TodoItem implements Serializable, Comparable<TodoItem> {
    // TODO: edit this class as you want
    private String description;
    private boolean isDone;
    private Long timeStampCreation;
    private Long timeStampLastChanged;

    public TodoItem(String description, boolean isDone, Long timeStamp) {
        this.description = description;
        this.isDone = isDone;
        this.timeStampCreation = timeStamp;
        this.timeStampLastChanged = timeStamp;
    }

    public void setDone() {
        this.timeStampLastChanged = System.currentTimeMillis();
        this.isDone = true;
    }

    public void setInProgress() {
        this.timeStampLastChanged = System.currentTimeMillis();
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.timeStampLastChanged = System.currentTimeMillis();
        this.description = description;
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
        } else if ((!this.isDone && !o.isDone) || (this.isDone && o.isDone)) {
            return Long.compare(this.timeStampCreation, o.timeStampCreation);
        } else {
            return -1;
        }
    }

    public void setTimeStampLastChanged(Long ts) {
        this.timeStampLastChanged = ts;
    }

    public Long getTimeStampCreation() {
        return timeStampCreation;
    }

    public String getLastTimeChanges() {
        String lastModified = "last modified: ";
        if (TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - this.timeStampLastChanged) < 1) {
            return (lastModified +
                    TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - this.timeStampLastChanged)
                    + " minutes ago");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date dateLast = new Date(this.timeStampLastChanged);
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat formatterLast = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatterNow = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm");
            if (formatterLast.format(dateLast).equals(formatterNow.format(now))) {
                return lastModified + "Today at " + formatterHour.format(dateLast);
            }
            return lastModified + formatterLast.format(dateLast) + " at " + formatterHour.format(dateLast);

        }
    }

    public String getCreationTimeString() {
        Date date = new Date(this.timeStampCreation);
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");

        return "created on: " + formatter1.format(date) + " at " + formatter2.format(date);
    }
}

