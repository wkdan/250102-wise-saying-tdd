package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;
import app.standard.simpleDb.SimpleDb;

public class WiseSayingDbRepository {

    private final SimpleDb simpleDb;

    public WiseSayingDbRepository() {
        this.simpleDb = new SimpleDb("localhost", "root", "123a456s", "wiseSaying__test");
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        return null;
    }
}
