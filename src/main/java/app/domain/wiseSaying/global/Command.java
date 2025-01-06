package app.domain.wiseSaying.global;

public class Command {

    String actionName;
    String paramKey;
    String paramValue;

    public Command(String cmd) {
        String[] cmdBits = cmd.split("\\?");
        actionName = cmdBits[0];

        if(cmdBits.length < 2) {
            paramValue = "";
            return;
        }

        String param = cmdBits[1];

        String[] paramBits = param.split("=", 2);
        paramKey = paramBits[0];
        if(paramBits.length < 2) {
            paramValue = null;
            return;
        }

        paramValue = paramBits[1];
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam() {

        return paramValue;
    }
    // 쪼개기 작업
}
