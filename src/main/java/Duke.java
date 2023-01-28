import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Text declarations and initial greeting
        final String BARRIER = "____________________________________________________________";
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        final String GREETING = BARRIER + "\n\nAhoy there! I be\n" + LOGO + "\nWhat can I do for ye?\nFor a list of commands, type 'help' me hearties!\n" + BARRIER + "\n";
        final String EXIT = BARRIER + "\n\nFarewell! Hope to see ye again soon, ye scallywag!\n" + BARRIER;
        System.out.println(GREETING);

        // Scanner declaration and initial input
        final Scanner CONSOLE = new Scanner(System.in);
        String input = CONSOLE.nextLine();

        // Read input such that it is not "bye"
        while(!input.equals("bye")) {

            // Split user input to check for dynamism
            String originalInput = input;
            String[] splitInput = input.split(" ");
            input = splitInput[0];
            int numTasks = Task.getTasksArray().size();

            // Switch to check input
            switch(input){

                // Lists all tasks using the Task class printAllTask() method
                case "list": {
                    System.out.println(BARRIER + "\n");
                    if(numTasks == 0) {
                        System.out.println("Ye have no tasks yet, ye lazy buccaneer!");
                    } else {
                        Task.printAllTasks();
                    }
                    System.out.println(BARRIER + "\n");
                    break;
                }

                // Marks a task as complete
                case "mark": {
                    char dynamicInput = splitInput[1].charAt(0);
                    if (!Character.isDigit(dynamicInput) || dynamicInput == '0' || splitInput[1].length() > 1) {
                        System.out.println("\nBlast! That isn't a valid number lad!\n" + BARRIER + "\n");
                    } else {
                            int index = Integer.parseInt(splitInput[1]) - 1;
                        if (index > numTasks - 1){
                            System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + BARRIER + "\n");
                        } else {
                            Task.getTasksArray().get(index).markAsComplete();
                        }
                    }
                    break;
                }

                // Unmarks a previous completed task
                case "unmark": {
                    char dynamicInput = splitInput[1].charAt(0);
                    if (!Character.isDigit(dynamicInput) || dynamicInput == '0' || splitInput[1].length() > 1) {
                        System.out.println("\nBlast! That isn't a valid number lad!\n" + BARRIER + "\n");
                    } else {
                            int index = Integer.parseInt(splitInput[1]) - 1;
                        if (index > numTasks - 1){
                            System.out.println("\nBlast! That task does not exist, please add tasks first, ye landlubbers!\n" + BARRIER + "\n");
                        } else {
                            Task.getTasksArray().get(index).unmarkAsComplete();
                        }
                    }
                    break;
                }

                // Prints a list of commands and description
                case "help": {
                    printHelpList();
                    break;
                }

                // Adds a new ToDo task
                case "todo": {
                    System.out.println(BARRIER + "\n");
                    Todo temp = new Todo(originalInput);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(BARRIER + "\n");
                    break;
                }

                // Adds a new Deadline task
                case "deadline": {
                    // Command input starts after '/'
                    int commandStart = originalInput.indexOf("/");
                    // Due date starts after '/by '
                    String dueDate = originalInput.substring(commandStart + 4);
                    String parsedDescription = originalInput.substring(0, commandStart - 1);
                    parsedDescription += " (by: " + dueDate + ")";
                    // Remove the word 'deadline' from the description
                    parsedDescription = parsedDescription.substring(parsedDescription.indexOf(" ") + 1);
                    System.out.println(BARRIER + "\n");
                    Deadline temp = new Deadline(parsedDescription, dueDate);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(BARRIER + "\n");
                    break;
                }

                // Adds a new Event task
                case "event": {
                    // Split input into description, start, and end
                    String[] splitEventInput = originalInput.split("/");
                    // Start time comes after '/from'
                    String start = splitEventInput[1].substring(5);
                    // End time comes after '/to'
                    String end = splitEventInput[2].substring(3);
                    String parsedDescription = splitEventInput[0] + "(from: " + start + "to: " + end + ")";
                    parsedDescription = parsedDescription.substring(parsedDescription.indexOf(" ") + 1);
                    System.out.println(BARRIER + "\n");
                    Event temp = new Event(parsedDescription, start, end);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(BARRIER + "\n");
                    break;
                }

                // Adds a new task if no case is hit
                default: {
                    System.out.println(BARRIER + "\n");
                    Task temp = new Task(input);
                    System.out.println("added: " + temp.printTask());
                    printListLength();
                    System.out.println(BARRIER + "\n");
                }
            }

            // Reads next input
            input = CONSOLE.nextLine();
        }

        System.out.println(EXIT);
    }

    // Local Duke method to list all functions
    public static void printHelpList() {
        String BARRIER = "____________________________________________________________";
        System.out.println(BARRIER + "\n\nAvast! Here be the commands ye can use to make me do yer bidding!\n- list: lists all current tasks\n- mark x: marks task x as complete\n- unmark x: unmarks task x as complete\n- todo 'description': adds a task to do with the given description\n- deadline 'description' /by 'deadline': adds a deadline task with the given date and description\n- event 'description' /from 'start' /to 'end': adds an event with the start and endtime\n- bye: exits Duke\n- anything else: adds a basic task with the given description\n" + BARRIER + "\n");
    }

    public static void printListLength() {
        int length = Task.getTasksArray().size();
        if (length == 1) {
            System.out.println("Now you have " + Task.getTasksArray().size() + " task in the list!");
        } else {
            System.out.println("Now you have " + Task.getTasksArray().size() + " tasks in the list!");
        }
    }
}