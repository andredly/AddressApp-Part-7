package ch.makery.address.ff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ExecuteShellComand {

    public static void main(String[] args) {

        ExecuteShellComand obj = new ExecuteShellComand();
        String domainName = "google.com";
        String command = "ping -n 3 " + domainName;
        String output = obj.executeCommand(command);
        System.out.println(output);

    }

    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream(), "cp866"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}