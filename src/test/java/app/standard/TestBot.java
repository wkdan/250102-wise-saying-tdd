package app.standard;

import app.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestBot {

    // static 붙이는 이유 -> 상태(데이터) 없는 객체기 때문(상태 없는 객체는 스태틱 사용해도 됨)

    public static String run(String input) {
        Scanner sc = new Scanner(input + "종료\n");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App app = new App(sc);
        app.run();

        return out.toString();
    }

    public static void makeSample(int cnt) {
        App app = new App(null);
        app.makeSampleData(cnt);
    }
}
