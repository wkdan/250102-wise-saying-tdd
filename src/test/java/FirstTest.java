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
//        App app = new App();
//        app.run();
//
//        assertThat("aaa").isEqualTo("aaa");
    }

    @Test
    void t3() {
        // 테스트봇 선입력

        String out = TestBot.run("");

        assertThat(out)
                .contains("명령 )")
                .contains("명언앱을 종료합니다.");
        // 출력값 체크
    }
    @Test
    @DisplayName("명령을 여러번 입력할 수 있다.")
    void t4() {
        String out = TestBot.run("""
                등록
                종료
                """);

        //명령 ) 횟수를 세서 검증해야 됨.
        long count = out.split("명령 \\)").length-1;

        System.out.println(count);

        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t5() {
        String out = TestBot.run("");

        assertThat(out)
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t6() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자 미상
                """); //선입력

        assertThat(out)
                .containsSubsequence("명언 : ", "작가 : ");
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력, 명언 번호 출력")
    void t7() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자 미상
                """); //선입력

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 - 명언 2개 입력, 명언 번호가 증가")
    void t8() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자 미상
                등록
                현재를 사랑하라.
                작자 미상
                """); //선입력

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.");
    }
}
