import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class DatePrinter {

    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        DateTimeWrapper dateTimeWrapper = new DateTimeWrapper(LocalDateTime.now());

        String outputDateTime = objectMapper.writeValueAsString(dateTimeWrapper);

        System.out.println(outputDateTime);
    }
}
