package app.domain.wiseSaying.global;

import java.util.HashMap;
import java.util.Map;

public class Command {

    String actionName;

    Map<String, String> paramMap;

    public Command(String cmd) {

        paramMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?");
        actionName = cmdBits[0];

        if(cmdBits.length < 2) {
            return;
        }

        String queryString = cmdBits[1];
        String[] params = queryString.split("&");

        for(String param : params) {
            String[] paramBits = param.split("=", 2);

            if(paramBits.length < 2) {
                continue;
            }

            paramMap.put(paramBits[0], paramBits[1]);
        }


    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key) {
        return paramMap.get(key);
    }
    // 쪼개기 작업
}
