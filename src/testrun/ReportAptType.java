package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class ReportAptType {

    private StringProperty monthYear = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private IntegerProperty total = new SimpleIntegerProperty();

    public ReportAptType() {
    }

    //Getters
    public StringProperty getMonthYear() {
        return this.monthYear;
    }

    public StringProperty getTitle() {
        return this.title;
    }

    public IntegerProperty getTotal() {
        return this.total;
    }

    //Setters
    public void setMonthYear(String monthYear) {
        this.monthYear.set(monthYear);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setTotal(int total) {
        this.total.set(total);
    }
}
