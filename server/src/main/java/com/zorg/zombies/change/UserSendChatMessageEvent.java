package com.zorg.zombies.change;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSendChatMessageEvent extends UserChange {

    private final boolean chatMessageCommand = true;
    private final String chatMessage;

    public UserSendChatMessageEvent(String id, String nickname, String chatMessage) {
        super(id, nickname);
        this.chatMessage = chatMessage;
    }
}
