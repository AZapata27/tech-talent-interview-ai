package co.com.flypass.langchain4j;

import co.com.flypass.models.AudioTest;
import co.com.flypass.models.InterviewText;
import co.com.flypass.models.VideoTest;
import co.com.flypass.ports.outbound.SpechRecognitionGenerator;

public class OpenAiSpechRecognitionGenerator implements SpechRecognitionGenerator {


    @Override
    public String generateTextFromAudio(AudioTest audioTest) {
        return "";
    }

    @Override
    public InterviewText generateTextFromVideo(VideoTest videoTest) {

        //Extraer audio del video primero


        //
        return null;
    }
}
