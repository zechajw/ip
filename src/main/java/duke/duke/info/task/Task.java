package duke.info.task;

public abstract class Task {

    private final String action;
    private boolean isComplete;
    private final String type;

    Task(String action, String type, boolean isComplete) {
        this.action = action;
        this.type = type;
        this.isComplete = isComplete; // task added is not complete by default
    }

    void complete() {
        this.isComplete = true;
    }

    void incomplete() {
        this.isComplete = false;
    }

    public String saveFormat() {
        return String.format("%s|%s|%s", this.type, this.isComplete ? "1" : "0", this.action);
    }

    @Override
    public String toString() {
        String product = "";
        if (isComplete) {
            product += "[X] ";
        } else {
            product += "[ ] ";
        }
        product += this.action;
        return product;
    }
}