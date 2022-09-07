package lt.jrgames.dragonsofmugloar.services.message;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "MessagesService", url = "${service.url}")
public interface MessageService {
}
