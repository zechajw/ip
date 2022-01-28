package duke.commands;

import duke.info.task.Calendar;
import duke.info.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public abstract class AddCommand extends Command {

    private final Task taskToAdd;

    /**
     * Constructs an AddCommand with the specified taskToAdd, which contains
     * the task to be added in the execute command.
     * @param taskToAdd - the task to be added
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Adds the task specified in taskToAdd to the specified calendar. Afterwards,
     * display a success message showing that the task has been successfully added
     * using the ui handler, and save the new version of the calendar to storage.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     */
    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        try {
            calendar.add(taskToAdd);
            ui.showTaskAdded(taskToAdd, calendar.numOfEntries());
            storage.save(calendar);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
