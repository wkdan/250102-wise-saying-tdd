package app.domain.wiseSaying.global.repository;

import app.domain.wiseSaying.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);

    List<WiseSaying> findAll();

    boolean deleteById(int id);

    Optional<WiseSaying> findById(int id);

}
