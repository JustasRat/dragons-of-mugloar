package lt.jrgames.dragonsofmugloar.client.logic.actions;

import lt.jrgames.dragonsofmugloar.client.model.GameContext;
import lt.jrgames.dragonsofmugloar.services.message.MessageService;
import lt.jrgames.dragonsofmugloar.services.message.model.Message;
import lt.jrgames.dragonsofmugloar.services.message.model.MessageResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolveMessageActionTest {
    @Mock
    private MessageService messageService;

    private final GameContext context = GameContext.builder()
            .id("test_game_id")
            .build();

    @Test
    void actionPerformed() {
        Message message = Message.builder()
                .adId("test_message_id")
                .message("test_message")
                .build();

        when(messageService.solve("test_game_id", "test_message_id")).thenReturn(MessageResult.builder()
                .score(234)
                .lives(3)
                .build());
        SolveMessageAction action = new SolveMessageAction(messageService, message);

        GameContext updatedContext = action.perform(context);

        verify(messageService).solve("test_game_id", "test_message_id");
        assertEquals("test_game_id", updatedContext.getId());
        assertEquals(3, updatedContext.getLives());
        assertEquals(234, updatedContext.getScore());
    }
}