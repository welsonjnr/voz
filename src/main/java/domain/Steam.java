package domain;

import interfaces.Execute;
import service.OpenProgram;

public class Steam implements Execute {

    public void execute(){
        OpenProgram.open("C:\\Program Files (x86)\\Steam\\steam.exe");
    }
}
