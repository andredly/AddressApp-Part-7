package ch.makery.address.ff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ExecuteShellComand {

    public static void main(String[] args) throws IOException {

        ExecuteShellComand obj = new ExecuteShellComand();
        String domainName = "google.com";
        String command = "ping -n 3 " + domainName;
        String output = obj.executeCommand(command);
        System.out.println(output);

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