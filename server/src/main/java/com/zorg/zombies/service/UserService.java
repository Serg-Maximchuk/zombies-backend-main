package com.zorg.zombies.service;

import com.zorg.zombies.model.User;
import com.zorg.zombies.model.UserData;
import com.zorg.zombies.persistence.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserIdDefiner userIdDefiner;
    private final UserDataRepository userDataRepository;
    private final UsersCommunicator usersCommunicator;


    public User createUser(String sessionId) {
        String id = userIdDefiner.getUserId(sessionId);
        UserData user = new UserData(id);
        return new User(userDataRepository.save(user), usersCommunicator);
    }
}
