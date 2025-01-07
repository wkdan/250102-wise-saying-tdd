package app.domain.wiseSaying.global.repository;

import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository{

    private static final String DB_PATH = "db/test/wiseSaying/";

    private final List<WiseSaying> wiseSayingList;
    private int lastId;

    public WiseSayingFileRepository() {
        wiseSayingList = new ArrayList<>();
        System.out.println("파일 DB 사용");
    }

    public WiseSaying save(WiseSaying wiseSaying) {


        Util.Json.writeAsMap(getFilePath(id),wiseSaying.toMap());
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingList;
    }

    public boolean deleteById(int id) {
        return Util.File.delete(getFilePath(id));
    }

    public Optional<WiseSaying> findById(int id) {
        Optional<WiseSaying> opWiseSaying = wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

        return opWiseSaying;

    }
    private String getFilePath(int id){
        return DB_PATH + id + ".json";
    }
}
