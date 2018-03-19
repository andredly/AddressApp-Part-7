package ch.makery.address.ff;

import java.io.IOException;
import java.util.concurrent.Callable;

public class ProcessTask implements Callable<Void> {

    private String command;
    private String serverPath;

    public ProcessTask(String serverPath, String command) {
        this.command = command;
        this.serverPath = serverPath;
    }

    @Override
    public Void call()  {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "start","cmd.exe","/k", "\"cd "+ serverPath + "&& "+command+"\"");
        builder.redirectErrorStream(true);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}