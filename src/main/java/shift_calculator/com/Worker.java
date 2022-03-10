package shift_calculator.com;

import java.time.LocalDate;
import java.util.Calendar;

public class Worker {

    private WorkingStatus workingStatus;

    public Worker(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

}
