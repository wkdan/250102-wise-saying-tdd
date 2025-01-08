package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository {

    private static final String DB_PATH = "db/test/wiseSaying/";
    private int lastId;
    public WiseSayingFileRepository() {
        System.out.println("파일 DB 사용");
        lastIdInit();
    }

    public void lastIdInit() {
        if(!Util.File.exists(DB_PATH + "lastId.txt")) {
            Util.File.createFile(DB_PATH + "lastId.txt");
        }
    }
    public WiseSaying save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()) {
            wiseSaying.setId(getLastId() + 1);
        }

        Util.Json.writeAsMap(getFilePath(wiseSaying.getId()), wiseSaying.toMap());

        setLastId(wiseSaying.getId());

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {

        return Util.File.getPaths(DB_PATH).stream()
                .map(Path::toString)
                .filter(path -> path.endsWith(".json"))
                .map(Util.Json::readAsMap)
                .map(WiseSaying::fromMap)
                .toList();
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

    private String getFilePath(int id) {
        return DB_PATH + id + ".json";
    }

    public int getLastId() {
        String idStr = Util.File.readAsString(DB_PATH + "lastId.txt");

        if (idStr.isEmpty()) {
            return 0;
        }
        try {
           return Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setLastId(int id) {
        Util.File.write(DB_PATH + "lastId.txt", id);
    }

}