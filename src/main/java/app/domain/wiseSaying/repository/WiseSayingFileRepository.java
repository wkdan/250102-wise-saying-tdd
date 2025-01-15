package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Page;
import app.domain.wiseSaying.WiseSaying;
import app.global.AppConfig;
import app.standard.Util;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository {

    private static final String DB_PATH = AppConfig.getDbPath() + "/wiseSaying";
    private static final String ID_FILE_PATH = DB_PATH + "/lastId.txt";
    private static final String BUILD_PATH = DB_PATH + "/build/data.json";

    public WiseSayingFileRepository() {
        System.out.println("파일 DB 사용");
        init();
    }

    public void init() {
        if (!Util.File.exists(ID_FILE_PATH)) {
            Util.File.createFile(ID_FILE_PATH);
        }

        if (!Util.File.exists(DB_PATH)) {
            Util.File.createDir(DB_PATH);
        }
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        boolean isNew = wiseSaying.isNew();

        if (isNew) {
            wiseSaying.setId(getLastId() + 1);
        }

        Util.Json.writeAsMap(getFilePath(wiseSaying.getId()), wiseSaying.toMap());

        if (isNew) {
            setLastId(wiseSaying.getId());
        }

        return wiseSaying;
    }

    public Page<WiseSaying> findByKeyword(String ktype, String kw, int itemsPerPage, int page) {

        List<WiseSaying> searchedWiseSayings = findAll().stream()
                .filter(w -> {
                    if (ktype.equals("content")) {
                        return w.getContent().contains(kw);
                    } else {
                        return w.getAuthor().contains(kw);
                    }
                })
                .sorted(Comparator.comparing(WiseSaying::getId).reversed()) // 기본은 오름차순. 내림차순
                .toList();

        return pageOf(searchedWiseSayings, itemsPerPage, page);
    }

    @Override
    public void createTable() {
        // 테이블 삭제
        Util.File.deleteForce(DB_PATH);
        // 새 테이블 생성
        Util.File.createDir(DB_PATH);
    }

    @Override
    public void truncateTable() {
        // 기존의 명언 데이터와 lastId 삭제
        Util.File.deleteForce(DB_PATH);
    }

    public Page<WiseSaying> findAll(int itemsPerPage, int page) {
        List<WiseSaying> sortedWiseSayings = findAll().stream()
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(sortedWiseSayings, itemsPerPage, page);
    }

    List<WiseSaying> findAll() {
        return Util.File.getPaths(DB_PATH).stream()
                .map(Path::toString)
                .filter(path -> path.endsWith(".json"))
                .map(Util.Json::readAsMap)
                .map(WiseSaying::fromMap)
                .toList();

    }

    private Page<WiseSaying> pageOf(List<WiseSaying> wiseSayings, int itemsPerPage, int page) {
        int totalItems = wiseSayings.size();

        List<WiseSaying> pageContent = wiseSayings.stream()
                .skip((long) (page - 1) * itemsPerPage)
                .limit(itemsPerPage)
                .toList();

        return new Page<>(pageContent, totalItems, itemsPerPage, page);
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

    public static String getFilePath(int id) {
        return DB_PATH + "/" + id + ".json";
    }

    public int getLastId() {
        String idStr = Util.File.readAsString(ID_FILE_PATH);

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
        Util.File.write(ID_FILE_PATH, id);
    }

    public void build() {

        List<Map<String, Object>> mapList = findAll().stream()
                .map(WiseSaying::toMap)
                .toList();

        String jsonStr = Util.Json.listToJson(mapList);
        Util.File.write(BUILD_PATH, jsonStr);
    }

    @Override
    public void makeSampleData(int cnt) {
        for (int i = 1; i <= cnt; i++) {
            WiseSaying wiseSaying = new WiseSaying("명언" + i, "작가" + i);
            save(wiseSaying);
        }
    }

    public static String getBuildPath() {
        return BUILD_PATH;
    }

    public int count() {
        return findAll().size();
    }
}