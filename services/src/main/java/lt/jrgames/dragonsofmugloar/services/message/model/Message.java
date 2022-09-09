package lt.jrgames.dragonsofmugloar.services.message.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@JsonDeserialize(using = MessageDeserializer.class)
public class Message {
    private String adId;
    private String message;
    private Integer reward;
    private Integer expiresIn;
    private Probability probability;
}
