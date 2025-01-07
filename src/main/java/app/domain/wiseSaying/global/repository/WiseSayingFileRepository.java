package app.domain.wiseSaying.global.repository;

import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository{

    private static final String DB_PATH = "db/test/wiseSaying/";

    public WiseSayingFileRepository() {
        wiseSayingList = new ArrayList<>();
        System.out.println("파일 DB 사용");
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        Util.Json.writeAsMap(getFilePath(wiseSaying.getId()),wiseSaying.toMap());
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        Util.File.getPaths(DB_PATH);
    }

    public boolean deleteById(int id) {
        return Util.File.delete(getFilePath(id));
    }

    public Optional<WiseSaying> findById(int id) {
        String path = getFilePath(id);
        Map<String, Object> map = Util.Json.readAsMap(path);

        if (map.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(WiseSaying.fromMap(map));
    }

    private String getFilePath(int id){
        return DB_PATH + id + ".json";
    }
}
