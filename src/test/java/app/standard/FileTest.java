package app.standard;

import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    // 3. 테스트 시작 전에 test 폴더 생성
    // 테스트 전처리

    @BeforeEach
    void beforeEach() {
        System.out.println("테스트 실행 전에 한번 실행");
        Util.File.createDir("test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("테스트 실행 후에 한번 실행");
        Util.File.deleteForce("test");
    }

    // 4. 테스트 종료 후에 test 폴더 삭제
    // 테스트 후처리

    @Test
    @DisplayName("최초의 파일 테스트")
    void t1() {
        Util.File.test();
    }

    @Test
    @DisplayName("파일 생성. 내용이 없는 빈 파일 생성")
    void t2() {

        String file = "test/test.txt";

        Util.File.createFile(file); // 파일 생성 ok

        assertThat(Files.exists(Paths.get(file)))
                .isTrue();
    }

    @Test
    @DisplayName("파일 내용 읽어오기")
    void t3() {

        // 파일을 Hello, World 내용으로 생성
        String file = "test/test.txt";
        String testContent = "Hello, World";

        Util.File.write(file, testContent);
        String content = Util.File.readAsString(file);

        assertThat(content)
                .isEqualTo(testContent);
    }

    @Test
    @DisplayName("파일 내용 수정")
    void t4() {

        String file = "test/test.txt";
        String writeContent = "modify content";

        Util.File.write(file, writeContent);
        String readContent = Util.File.readAsString(file);

        assertThat(readContent)
                .isEqualTo(writeContent);
    }

    @Test
    @DisplayName("파일 삭제")
    void t5() {

        String file = "test/test.txt";

        // test3.txt 파일 생성
        Util.File.createFile(file);
        assertThat(Files.exists(Paths.get(file)))
                .isTrue();

        // test3.txt 파일 삭제
        Util.File.delete(file);

        // test3.txt 존재 여부 확인
        assertThat(Files.exists(Paths.get(file)))
                .isFalse();

    }

    @Test
    @DisplayName("폴더 생성")
    void t6() {

        String dirPath = "test";

        Util.File.createDir(dirPath);

        assertThat(Files.exists(Paths.get(dirPath)))
                .isTrue();

        assertThat(Files.isDirectory(Path.of(dirPath)))
                .isTrue();

    }

    @Test
    @DisplayName("폴더 삭제")
    void t7() {

        String dirPath = "test";

        Util.File.delete(dirPath);

        assertThat(Files.exists(Paths.get(dirPath)))
                .isFalse();
    }

    @Test
    @DisplayName("파일 생성 -> 없는 폴더에 생성 시도하면 폴더를 생성한 후에 파일 생성")
    void t8() {
        String path = "test/test2/test.txt";

        Util.File.createFile(path);

        boolean rst = Files.exists(Paths.get(path));
        assertThat(rst)
                .isTrue();
    }

    @Test
    @DisplayName("파일 삭제 -> 폴더가 비어있지 않을 때 안의 내용까지 같이 삭제")
    void t9() {
        String path = "test/test2/test.txt";

        Util.File.deleteForce(path); // 강제 삭제

        boolean rst = Files.exists(Paths.get(path));
        assertThat(rst)
                .isFalse();
    }

    @Test
    @DisplayName("특정 폴더의 파일 목록을 가져오기")
    void t10() {

        String path1 = "test/test1.txt";
        String path2 = "test/test2.txt";
        String path3 = "test/test3.txt";

        Util.File.write(path1, "test1");
        Util.File.write(path2, "test2");
        Util.File.write(path3, "test3");

        assertThat(Files.exists(Paths.get(path1)))
                .isTrue();
        assertThat(Files.exists(Paths.get(path2)))
                .isTrue();
        assertThat(Files.exists(Paths.get(path3)))
                .isTrue();

        List<Path> paths = Util.File.getPaths("test/");

        assertThat(paths)
                .hasSize(3)
                .contains(Paths.get(path1))
                .contains(Paths.get(path2))
                .contains(Paths.get(path3));
    }
}