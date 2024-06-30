package domain;

import interfaces.Execute;
import lombok.Data;

public class Speak implements Execute {

    public Speak(){};

    public void execute(){
        System.out.println("SHHHH!!");
    }
}
