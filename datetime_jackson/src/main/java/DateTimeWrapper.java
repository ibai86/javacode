import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DateTimeWrapper(

        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'##'HH:mm:ss:SSS",
                locale = "ru-RU")
        LocalDateTime timestamp
) {
}
