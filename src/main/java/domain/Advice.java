package domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Execute;
import lombok.Data;
import repository.Request;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Advice implements Execute {
    public static final String URL_GET = "https://api.adviceslip.com/advice";

    @Override
    public void execute()  {
        try {
            HttpResponse<String> response = Request.requestGetAdvice(URL_GET);

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                ObjectMapper objectMapper = new ObjectMapper();
                AdviceResponse adviceResponse = objectMapper.readValue(responseBody, AdviceResponse.class);

                System.out.println(adviceResponse.getSlip().getAdvice());
            } else {
                System.out.println("Erro: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String returnAdvice() throws IOException, InterruptedException {
        HttpResponse<String> response = Request.requestGetAdvice(URL_GET);

        if (response.statusCode() == 200) {
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            AdviceResponse adviceResponse = objectMapper.readValue(responseBody, AdviceResponse.class);

            return adviceResponse.getSlip().getAdvice();
        }
        return "No advice today";
    }
}

@Data
class Slip {
    private int id;
    private String advice;
}

@Data
class AdviceResponse {
    private Slip slip;
}
