package utc2.itk62.store.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FromAndToDate {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public FromAndToDate(LocalDate fromDate, LocalDate toDate) {

        this.fromDate = fromDate.withDayOfMonth(1).atStartOfDay();
        this.toDate = toDate.atTime(23, 59, 59);
    }

    public FromAndToDate() {
        LocalDate today = LocalDate.now();
        this.fromDate = today.withDayOfMonth(1).atStartOfDay();
        this.toDate = today.atTime(23, 59, 59);
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}
