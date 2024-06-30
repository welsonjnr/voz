package service;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class Voice {
    private LiveSpeechRecognizer recognizer;
    private String resultRecognizer;
    private boolean isRunning = false;
    public boolean cleanCacheMic = true;

    public Voice() {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setGrammarPath("resource:/grammars");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);
        try {
            if(cleanCacheMic)
                recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void startRecognizer() {
        if(isRunning)
            System.out.println("I can already hear you!");
        else {
            isRunning = true;
            recognizer.startRecognition(cleanCacheMic);
            cleanCacheMic = false;
            System.out.println("Listening...");
            try {
                while(isRunning) {
                    SpeechResult speechResult = recognizer.getResult();
                    if (speechResult == null)
                        System.out.println("I can't understand what you said.");
                    else {
                        resultRecognizer = speechResult.getHypothesis();
                        System.out.println("You said: '" + resultRecognizer + "'");

                        if(resultRecognizer.equalsIgnoreCase("close") || resultRecognizer.equalsIgnoreCase("stop")){
                            isRunning = false;
                            cleanCacheMic = true;
                            break;
                        }

                        Execute clazz = new Execute(resultRecognizer);
                        clazz.execute();

                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
                isRunning = false;
            }
            System.out.println("Finish :) ");
            recognizer.stopRecognition();
        }
    }
}
