package lt.jrgames.dragonsofmugloar.services.message;

import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "MessagesService", url = "${service.url}")
public interface MessageService {
    @RequestMapping(method = GET, value = "/api/v2/{gameId}/messages")
    List<Message> availableMessages(@PathVariable String gameId);

    @RequestMapping(method = POST, value = "/api/v2/{gameId}/solve/{messageId}")
    MessageResult solve(@PathVariable String gameId, @PathVariable String messageId);
}
