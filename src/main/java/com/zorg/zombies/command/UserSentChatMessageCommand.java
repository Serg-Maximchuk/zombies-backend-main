package com.zorg.zombies.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSentChatMessageCommand extends Command {

    public static final String CHAT_MESSAGE_FIELD = "chatMessage";

    private final String chatMessage;

    {
        chatMessageCommand = true;
    }
}
