package com.zorg.zombies.service;

import com.zorg.zombies.addon.chat.Chat;
import com.zorg.zombies.change.WorldChange;
import com.zorg.zombies.command.Command;
import com.zorg.zombies.command.ErrorCommand;
import com.zorg.zombies.command.UserMoveCommand;
import com.zorg.zombies.command.UserSentChatMessageCommand;
import com.zorg.zombies.command.UserStartGameCommand;
import com.zorg.zombies.command.UserStopMoveCommand;
import com.zorg.zombies.model.Coordinates;
import com.zorg.zombies.model.User;
import com.zorg.zombies.service.exception.WrongMoveCommandException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserActionsProcessor extends FluxProcessorDelegatingSubscriber<Command, WorldChange> {

    private final User user;
    private final Chat chat;

    UserActionsProcessor(User user, Chat chat) {
        super(user.getSubscriber());
        this.user = user;
        this.chat = chat;
    }

    @Override
    public void onNext(Command command) {
        if (command.isMoveChangeCommand()) {
            if (command.isMoveStartCommand()) {
                user.act((UserMoveCommand) command);
            } else if (command.isMoveStopCommand()) {
                user.act((UserStopMoveCommand) command);
            } else {
                throw new WrongMoveCommandException(command);
            }
        } else if (command.isChatMessageCommand()) {
            chat.sendMessage(
                    user.getId(),
                    user.getNickname(),
                    ((UserSentChatMessageCommand) command).getChatMessage()
            );
        } else if (command.isStartGameCommand()) {
            UserStartGameCommand startGameCommand = (UserStartGameCommand) command;
            user.setNickname(startGameCommand.getNickname());
            user.setPosition(new Coordinates());
            user.notifyJoining();

        } else if (command.isErrorCommand()) {
            ErrorCommand errorCommand = (ErrorCommand) command;
            log.error("Received error command", errorCommand.getError());
        }
    }

    @Override
    public void onComplete() {
        user.destroy();
    }

}
