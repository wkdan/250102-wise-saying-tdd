import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;


public class FirstTest {

    @Test
    void t1() {
        int rst = 1;
        assertThat(rst).isEqualTo(1);//실제 값, 비교군 비교해서 같으면 통과
    }

    @Test
    void t2() {
        TestApp app = new TestApp();
        app.run();

        assertThat("aaa").isEqualTo("aaa");
    }

    @Test
    void t3() {
        // 테스트봇 선입력
        Scanner sc = new Scanner("종료");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        TestApp app = new TestApp();
        app.run();

        assertThat(out.toString()).contains("명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t4() {
        // 테스트봇 선입력
        Scanner sc = new Scanner("종료");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        TestApp app = new TestApp();
        app.run();




        assertThat(out.toString())
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
        // 출력값 체크
    }
}
