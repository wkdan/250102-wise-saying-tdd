package app.domain.wiseSaying.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void test() {
        System.out.println("hihi");
    }
}
