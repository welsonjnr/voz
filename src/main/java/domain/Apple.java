package domain;

import interfaces.Execute;

public class Apple implements Execute {

    public Apple() {}

    @Override
    public void execute(){
        System.out.println("The apple fell, finally!!");
    }
}
