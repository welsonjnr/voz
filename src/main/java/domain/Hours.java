package domain;
import interfaces.Execute;
import lombok.Data;

import java.time.LocalDateTime;

public class Hours implements Execute {

    public Hours(){};

    @Override
    public void execute(){
        System.out.println(LocalDateTime.now());
    }
}
