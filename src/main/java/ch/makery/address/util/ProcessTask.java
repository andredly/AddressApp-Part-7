package ch.makery.address.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            Process start = builder.start();
            InputStream inputStream = start.getInputStream();
            OutputStream outputStream = start.getOutputStream();

            start.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}