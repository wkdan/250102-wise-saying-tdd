package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;
import app.domain.wiseSaying.global.repository.WiseSayingFileRepository;
import app.domain.wiseSaying.global.repository.WiseSayingRepository;
import app.standard.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    WiseSayingRepository wiseSayingRepository = new WiseSayingFileRepository();

    @Test
    @DisplayName("명언 저장")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying(1,"aaa","bbb");
        wiseSayingRepository.save(wiseSaying);

        String filePath = "db/wiseSaying/1.json";

        boolean rst = Files.exists(Path.of(filePath));
        assertThat(rst).isTrue();

        Map<String, Object> map = Util.Json.readAsMap(filePath);
        WiseSaying restoreWiseSaying = WiseSaying.fromMap(map);

        assertThat(wiseSaying).isEqualTo(restoreWiseSaying);
    }
}
