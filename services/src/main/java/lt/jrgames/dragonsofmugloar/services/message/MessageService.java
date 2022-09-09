package lt.jrgames.dragonsofmugloar.services.message;

import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "MessagesService", url = "${service.url}")
public interface MessageService {
    @GetMapping("/api/v2/{gameId}/messages")
    List<Message> availableMessages(@PathVariable String gameId);

    @PostMapping("/api/v2/{gameId}/solve/{messageId}")
    MessageResult solve(@PathVariable String gameId, @PathVariable String messageId);
}
