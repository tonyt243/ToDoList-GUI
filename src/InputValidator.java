import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputValidator {
    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false); // strict parsing
        try {
            sdf.parse(date); // try parsing
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
