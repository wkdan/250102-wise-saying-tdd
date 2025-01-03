import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FirstTest {

    @Test
    void t1() {
        int rst = 1;
        assertThat(rst).isEqualTo(1);//실제 값, 비교군 비교해서 같으면 통과
    }

    @Test
    void t2() {
        App app = new App();
        app.run();

        assertThat("aaa").isEqualTo("aaa");
    }

    @Test
    void t3() {
        // 테스트봇 선입력

        String out = TestBot.run("");

        assertThat(out)
                .contains("명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t4() {
        String out = TestBot.run("");

        assertThat(out)
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t5() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자 미상
                """); //선입력

        assertThat(out)
                .containsSubsequence("명언 : ", "작가 : ");
    }
}
