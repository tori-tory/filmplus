package ru.jabki.filmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.filmplus.exception.FriendException;
import ru.jabki.filmplus.model.Friend;
import ru.jabki.filmplus.repository.FriendRepository;

@Service
@AllArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional(rollbackFor =  Exception.class)
    public Friend create(final Friend friend) {
        if (friend.getUserId().equals(friend.getFriendId())) {
            throw new FriendException("Нельзя добавить самого себя в друзья");
        }
        validate(friend);

        return friendRepository.insert(friend);
    }

    @Transactional(rollbackFor =  Exception.class)
    public void delete(final Long id) {
        friendRepository.delete(id);
    }

    private void validate(final Friend friend) {
        userService.getById(friend.getUserId());
        userService.getById(friend.getFriendId());
    }

    @Transactional(readOnly = true)
    public Friend getById(final long id) {
        return friendRepository.getById(id);
    }
}