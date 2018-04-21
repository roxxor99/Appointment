
package testrun;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jed Gunderson
 */
public class Reports {
    private final StringProperty date;
    private final StringProperty title;
    private final IntegerProperty total;
    
    public Reports(String date, String title, int total) {
        this.date = new SimpleStringProperty(date);
        this.title = new SimpleStringProperty(title);
        this.total = new SimpleIntegerProperty(total);
    }

    public StringProperty date() {
        return date;
    }
    
    public StringProperty title() {
        return title;
    }
    
    public IntegerProperty total() {
        return total;
    }
}
