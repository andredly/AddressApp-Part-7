package ch.makery.address.util;

import ch.makery.address.view.MainController;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteShellComand {

    public static void main(String[] args) throws IOException {

        ExecuteShellComand obj = new ExecuteShellComand();
        String domainName = "google.com";
        String command = "ping -n 3 " + domainName;
        String output = obj.executeCommand(command);
        System.out.println(output);

    }

    public void executeCommand(String command, String serverPath) {

        ExecutorService service = Executors.newFixedThreadPool(2);
        ProcessTask processTask = new ProcessTask(serverPath, command);
        service.submit(processTask);
        String cmd = "cmd.exe "+ "/c "+ "start "+"cmd.exe "+ "/k "+ "\"cd "+ serverPath +"&& "+command+"\"";
        System.out.println(cmd);

//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line = null;
//        while (true) {
//            try {
//                line = r.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (line == null) { break; }
//            System.out.println(line);
//        }
    }

    public String executeCommand(String command) {
        System.getProperties();
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\Program Files\" && mvn --v");
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) { break; }
            System.out.println(line);
        }

//        String[] com = command.split(" ");
        String[] com = {"cmd"};
        StringBuffer output = new StringBuffer();
//        Process p;
        try {
            p = Runtime.getRuntime().exec(com);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream(), "cp866"));
            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}