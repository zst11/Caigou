import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
//        System.out.println(getSum(4));
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss EE");
        String s = simpleDateFormat.format(date);
        System.out.println(s);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(cal.get(0));
        System.out.println(cal.getTime());
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(ZoneId.systemDefault());
        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());
//        LocalTime.of(1,1,1)
        System.out.println(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(Instant.now().atZone(ZoneId.of("Asia/Shanghai"))));
    }
    public static int getSum(int x){
        if (x == 1) return 1;
        if (x == 2) return 2;
        return getSum(x - 1) + getSum(x - 2);
    }
}
