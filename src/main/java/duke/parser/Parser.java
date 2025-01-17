package duke.parser;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.RecurringEventCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.info.exception.InvalidCommandException;

public class Parser {

    Parser() {}

    /**
     * Returns a command as specified by the specified input fullCommand. This command
     * takes in a string line from I/O and converts it into a command depending on the
     * leading identifier keyword.
     * @param fullCommand - string command to be parsed
     * @return - Command from specified fullCommand
     * @throws InvalidCommandException - if the String cannot be parsed into a Command
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] splitCommand = fullCommand.split(" ", 2); // splits the first word from the command
        String command = splitCommand[0];
        String input = "";
        if (splitCommand.length > 1) {
            input = splitCommand[1];
            input = input.trim();
        }

        switch(command) {
        case "recurring":
            // format: recurring event hello /at 2 Dec 2019 /interval 7 /ends 1 Jan 2021
            String[] splitEndDate = splitCommand[1].split(" /ends ", 2);
            String endDate = splitEndDate[1];
            String[] splitInterval = splitEndDate[0].split(" /interval ", 2);
            int interval = Integer.valueOf(splitInterval[1]);
            String[] splitDate = splitInterval[0].split(" /at ", 2);
            String date = splitDate[1];
            String[] splitAction = splitDate[0].split(" ", 2);
            String event = splitAction[1];
            return new RecurringEventCommand(event, date, interval, endDate);
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            String[] splitDeadline = input.split(" /by ", 2); // splits the action from the date
            return new DeadlineCommand(splitDeadline[0], splitDeadline[1]);
        case "event":
            String[] splitEvent = input.split(" /at ", 2);
            return new EventCommand(splitEvent[0], splitEvent[1]);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new InvalidCommandException();
        }
    }
}
