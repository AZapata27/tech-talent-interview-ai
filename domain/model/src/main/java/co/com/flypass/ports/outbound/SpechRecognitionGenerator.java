package co.com.flypass.ports.outbound;

import co.com.flypass.models.AudioTest;
import co.com.flypass.models.InterviewText;
import co.com.flypass.models.VideoTest;

public interface SpechRecognitionGenerator {


    String generateTextFromAudio(AudioTest audioTest);

    InterviewText generateTextFromVideo(VideoTest videoTest);
}
