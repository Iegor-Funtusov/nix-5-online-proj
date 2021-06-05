package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.controller.CalendarController;
import ua.com.nkrasnovoronka.util.UserInput;

public class Main {

    public static void main(String[] args) {
        CalendarController calendarController = new CalendarController();
        while (true) {
            doAction(calendarController);
        }

    }

    private static void doAction(CalendarController calendarController) {
        int userInputNumber = UserInput.userInputNumber("Pleas chose action\n1 - Chose date format\n2 - Adding to date\n3 - Subtract from date\n4 - Difference in dates\n5 - Sort dates\n0 - to exit");
        switch (userInputNumber) {
            case 1 -> calendarController.chooseParserFormat();
            case 2 -> calendarController.addingService(calendarController.enterDate()).printCalendar(calendarController.getParserFormat());
            case 3 -> calendarController.subtractService(calendarController.enterDate()).printCalendar(calendarController.getParserFormat());
            case 4 -> calendarController.differenceService(calendarController.enterDate(), calendarController.enterDate());
            case 5 -> calendarController.calendarsSorting()
                    .forEach(calendar -> calendar.printCalendar(calendarController.getParserFormat()));
            case 0 -> System.exit(0);
            default -> {
                System.err.println("Wrong input!!! Pleas try again");
                doAction(calendarController);
            }
        }
    }
}
