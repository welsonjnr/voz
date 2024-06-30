package domain;

import interfaces.Execute;
import service.OpenProgram;

public class Opera implements Execute {

    private static final String USER_HOME = System.getProperty("user.home");

    public void execute(){
        String comando =  USER_HOME + "\\AppData\\Local\\Programs\\Opera GX\\opera.exe";
        OpenProgram.open(comando);
    }
}
