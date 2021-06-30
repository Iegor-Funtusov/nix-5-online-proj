import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
    public static Date parse(String date) throws ParseException {
        Date res;
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            res = sdf.parse(date);
        } catch (ParseException e) {
            try {
                sdf = new SimpleDateFormat("MM-dd-yyyy");
                sdf.setLenient(false);
                res = sdf.parse(date);
            } catch (ParseException ex) {
                sdf = new SimpleDateFormat("yyyy/MM/dd");
                sdf.setLenient(false);
                res = sdf.parse(date);
            }
        }
        return res;
    }
}
