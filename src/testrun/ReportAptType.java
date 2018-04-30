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
    private StringProperty month = new SimpleStringProperty();
    private StringProperty year = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private IntegerProperty total = new SimpleIntegerProperty();

    public ReportAptType() {
    }

    //Getters
    public StringProperty getMonth() {
        return this.month;
    }
    public StringProperty getYear() {
        return this.year;
    }

    public StringProperty getTitle() {
        return this.title;
    }

    public IntegerProperty getTotal() {
        return this.total;
    }

    //Setters
    public void setMonth(String month) {
        this.month.set(month);
    }
    
    public void setYear(String year) {
        this.year.set(year);
    }
    
    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setTotal(int total) {
        this.total.set(total);
    }
}
