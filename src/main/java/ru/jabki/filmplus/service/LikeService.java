package ru.jabki.filmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.filmplus.model.Like;
import ru.jabki.filmplus.repository.LikeRepository;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final FilmService filmService;

    @Transactional(rollbackFor =  Exception.class)
    public Like create(Like like){
        validate(like);
        return likeRepository.insert(like);
    }

    @Transactional(rollbackFor =  Exception.class)
    public void delete(Long userId, Long filmId){
        likeRepository.delete(userId, filmId);
    }

    private void validate(final Like like) {
        userService.getById(like.getUserId());
        filmService.getById(like.getFilmId());
    }
}