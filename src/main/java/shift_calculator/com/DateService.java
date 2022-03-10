package shift_calculator.com;

import java.time.LocalDate;
import java.util.*;

public class DateService {

    private LocalDate currentDate;
    private LocalDate inputDate;
    private Map<WorkingStatus, List<LocalDate>> workingDatesStatusList = new HashMap<>();
    private WorkingStatus currentWorkingStatus;
    private Worker worker;

    public DateService(LocalDate date, LocalDate inputDate, WorkingStatus currentWorkerStatus, Worker worker) {
        this.currentDate = date;
        this.inputDate = inputDate;
        this.currentWorkingStatus = currentWorkerStatus;
        this.worker = worker;
    }


    public void calculateShiftFromDateInput(){

        if (currentDate.isBefore(inputDate)){
            iterateBetweenDates(currentWorkingStatus, currentDate, inputDate);
        }
        else if(currentDate.isAfter(inputDate)){
            iterateBetweenDates(currentWorkingStatus, inputDate, currentDate);
        }
        System.out.println(workingDatesStatusList);
    }

    void iterateBetweenDates(WorkingStatus workingStatus,LocalDate start, LocalDate end) {

        worker.setWorkingStatus(workingStatus);
        int notWorkingDaysCounter = 0;

        LocalDate date = start;
        if (date.isBefore(end)) {
            while (date.isBefore(end) || date.equals(end)) {

                if (worker.getWorkingStatus().equals(WorkingStatus.DAY_SHIFT)) {
                    addToList(date);
                    worker.setWorkingStatus(WorkingStatus.NIGHT_SHIFT);
                } else if (worker.getWorkingStatus().equals(WorkingStatus.NIGHT_SHIFT)) {
                    addToList(date);
                    worker.setWorkingStatus(WorkingStatus.IS_NOT_WORKING);
                    notWorkingDaysCounter++;
                } else if (worker.getWorkingStatus().equals(WorkingStatus.IS_NOT_WORKING) && notWorkingDaysCounter == 1) {
                    addToList(date);
                    notWorkingDaysCounter++;
                } else if (worker.getWorkingStatus().equals(WorkingStatus.IS_NOT_WORKING) && notWorkingDaysCounter == 2) {
                    addToList(date);
                    worker.setWorkingStatus(WorkingStatus.DAY_SHIFT);
                    notWorkingDaysCounter = 0;
                }
                date = date.plusDays(1);
            }
        }
    }


    private void addToList(LocalDate date) {

        workingDatesStatusList.computeIfAbsent(worker.getWorkingStatus(), status -> new ArrayList<>());
        workingDatesStatusList.get(worker.getWorkingStatus()).add(date);
    }


}
