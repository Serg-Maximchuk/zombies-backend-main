package com.zorg.zombies.addon.chat;

import com.zorg.zombies.change.UserSendChatMessageEvent;
import com.zorg.zombies.change.WorldChange;
import com.zorg.zombies.service.UsersCommunicator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Chat {

    private final UsersCommunicator usersCommunicator;


    public void sendMessage(String id, String nickname, String chatMessage) {
        usersCommunicator.notifyUsers(new WorldChange<>(new UserSendChatMessageEvent(
                id, nickname, chatMessage
        )));
    }
}
