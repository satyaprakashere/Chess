package games.chess;

/**
 * Created by Satya on 13/06/14.
 */
public class Main {

    public static void main(String[] args) {
        if (!checkSystemRequirements()) {
            System.exit(-1);
        }
        System.out.print("Haha");
        ChessFx.launch(args);
    }

    public static boolean checkSystemRequirements() {
        String javaVersion = System.getProperty("java.runtime.version");

        if (javaVersion == null) {
            javaVersion = System.getProperty("java.version");
        }
        System.out.println("java version : " + javaVersion);
        if (javaVersion==null || javaVersion.compareTo("1.8")<0) {

            if (javaVersion==null)
                javaVersion = "unknown !?";

//            new ErrorMessage(null,
//                    "Java Runtime Environment 1.8 or later required !\n"+
//                            "Your current version is "+javaVersion+"\n"+
//                            "     http://java.sun.com/j2se/downloads.html");

            return false;
        }
        return true;

    }
}
