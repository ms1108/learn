package exemvn;

import java.io.IOException;

public class ExeMvn {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        String exec;
        String property = System.getProperty("user.dir");
        try {
            //runtime.exec("cmd /k cd " + property + " && mvn package");
            runtime.exec("cd "+property+" && mvn package");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

