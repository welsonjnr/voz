package service;

import lombok.Data;

import java.io.IOException;

@Data
public class OpenProgram {

    public static void open(String comando){

            ProcessBuilder processBuilder = new ProcessBuilder(comando);

            try {
                Process processo = processBuilder.start();

                int out = processo.waitFor();
                System.out.println("O processo terminou com o código de saída: " + out);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
