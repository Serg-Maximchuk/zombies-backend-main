package com.zorg.zombies.service;

import com.zorg.zombies.addon.chat.Chat;
import com.zorg.zombies.change.WorldChange;
import com.zorg.zombies.command.Command;
import com.zorg.zombies.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.FluxProcessor;

@Component
@RequiredArgsConstructor
public class GameSupervisor {

    private final UserService userService;
    private final Chat chat;


    public FluxProcessor<Command, WorldChange> createGameActionsProcessor(String sessionId) {
        User user = userService.createUser(sessionId);
        UserActionsProcessor processor = new UserActionsProcessor(user, chat);

        return FluxProcessor.wrap(processor, processor.doOnTerminate(user::destroy));
    }

}
