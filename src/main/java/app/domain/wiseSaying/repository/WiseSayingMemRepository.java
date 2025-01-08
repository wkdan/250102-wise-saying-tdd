package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingMemRepository implements WiseSayingRepository {

    private final List<WiseSaying> wiseSayingList;
    private int lastId;

    public WiseSayingMemRepository() {
        wiseSayingList = new ArrayList<>();
        System.out.println("메모리 DB 사용");
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        // 명언 등록/수정 구별

        if (!wiseSaying.isNew()) {
            return wiseSaying;
        }

        int id = ++lastId;
        wiseSaying.setId(id);
        wiseSayingList.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingList;
    }

    public boolean deleteById(int id) {
        return wiseSayingList.removeIf(w -> w.getId() == id); // 삭제 성공 true, 실패 false
    }

    public Optional<WiseSaying> findById(int id) {
        Optional<WiseSaying> opWiseSaying = wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

        return opWiseSaying;
    }
}