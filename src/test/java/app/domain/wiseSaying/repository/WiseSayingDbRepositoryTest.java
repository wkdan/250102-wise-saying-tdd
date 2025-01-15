package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingDbRepositoryTest {

    private static WiseSayingDbRepository wiseSayingDbRepository = new WiseSayingDbRepository();

    @BeforeAll
    static void dropTable() {
        wiseSayingDbRepository.createWiseSayingTable();
    }

    @BeforeEach
    void truncateArticleTable() {
        wiseSayingDbRepository.truncateWiseSayingTable();

    }

    @Test
    @DisplayName("save, findByID 테스트")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("현재를 사랑하라", "작자 미상");
        wiseSaying = wiseSayingDbRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying= wiseSayingDbRepository.findById(wiseSaying.getId());

        WiseSaying found = opWiseSaying.orElse(null);

        assertThat(wiseSaying.getId()).isEqualTo(1);
        assertThat(found).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("명언 삭제")
    void t2() {

        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");

        wiseSaying = wiseSayingDbRepository.save(wiseSaying);

        boolean delRst = wiseSayingDbRepository.deleteById(wiseSaying.getId());

        assertThat(delRst).isTrue();
    }

    @Test
    @DisplayName("모든 명언 가져오기")
    void t3() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa1", "bbb1");
        WiseSaying wiseSaying2 = new WiseSaying("aaa2", "bbb2");
        WiseSaying wiseSaying3 = new WiseSaying("aaa3", "bbb3");

        wiseSayingDbRepository.save(wiseSaying1);
        wiseSayingDbRepository.save(wiseSaying2);
        wiseSayingDbRepository.save(wiseSaying3);

        List<WiseSaying> wiseSayings = wiseSayingDbRepository.findAll();

        assertThat(wiseSayings).hasSize(3);
        assertThat(wiseSayings).contains(wiseSaying1, wiseSaying2, wiseSaying3);

    }
}
