import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
    
public static void main(String[] args) {
    LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss a");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);
}
}
