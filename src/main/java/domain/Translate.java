package domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Execute;
import lombok.Data;
import repository.Request;

import java.net.http.HttpResponse;

public class Translate implements Execute {
    public static final String URL_POST = "https://api.funtranslations.com/translate/mandalorian.json";

    public void execute(){
        try {
            Advice advice = new Advice();
            String textToTranslate = advice.returnAdvice();

            HttpResponse<String> response = Request.requestPostTranslate(URL_POST, textToTranslate);

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                ObjectMapper objectMapper = new ObjectMapper();
                TranslationResponse adviceResponse = objectMapper.readValue(responseBody, TranslationResponse.class);

                System.out.println("Language : " + adviceResponse.getContents().getTranslation());
                System.out.println("Original : " + adviceResponse.getContents().getText());
                System.out.println("Translated : " + adviceResponse.getContents().getTranslated());
            } else {
                System.out.println("Erro: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

@Data
class Success {
    private int total;
}

@Data
class Contents {
    private String translated;
    private String text;
    private String translation;
}

@Data
class TranslationResponse {
    private Success success;
    private Contents contents;
}
