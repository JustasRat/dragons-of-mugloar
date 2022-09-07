package lt.jrgames.dragonsofmugloar.client;

import lt.jrgames.dragonsofmugloar.services.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfiguration.class)
public class DragonsOfMugloarConfiguration {
}
