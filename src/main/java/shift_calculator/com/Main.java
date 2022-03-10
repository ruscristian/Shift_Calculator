package shift_calculator.com;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalDate currentDate = LocalDate.now();
        Worker worker = new Worker(WorkingStatus.IS_NOT_WORKING);
        LocalDate inputDate = null;



        System.out.println("Enter date to scan in format \"dd.MM.yyyy\"(day.month.year): ");
        String dateEndpoint = scanner.nextLine();
        System.out.println("Enter your current working status: ");
        String currentWorkingStatus = scanner.nextLine();

        inputDate = LocalDate.parse(dateEndpoint, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        if(currentWorkingStatus.equals("no")){
            worker.setWorkingStatus(WorkingStatus.IS_NOT_WORKING);
        }
        if(currentWorkingStatus.equals("n")){
            worker.setWorkingStatus(WorkingStatus.NIGHT_SHIFT);
        }
        if(currentWorkingStatus.equals("d")){
            worker.setWorkingStatus(WorkingStatus.DAY_SHIFT);
        }

        DateService dateService = new DateService(currentDate, inputDate, worker.getWorkingStatus(), worker);

        dateService.calculateShiftFromDateInput();
        }
    }
