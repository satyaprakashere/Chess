package game.chess;

/**
 * Created by Satya on 13/06/14.
 */
public class Main {

    public static void main(String[] args) {
        if (!checkSystemRequirements()) {
            System.exit(-1);
        }
//        ChessFx.launch(args);
    }

    public static boolean checkSystemRequirements() {
        String javaVersion = System.getProperty("java.runtime.version");
        if (javaVersion == null) {
            javaVersion = System.getProperty("java.version");
        }
        if (javaVersion==null || javaVersion.compareTo("1.8")<0) {
            System.out.println("Minimun java version 8 required");
            return false;
        }
        return true;

    }
}
