package co.com.flypass.usecase;

import co.com.flypass.models.InterviewText;
import co.com.flypass.models.VideoTest;
import co.com.flypass.ports.outbound.SpechRecognitionGenerator;
import co.com.flypass.ports.outbound.VideoRepository;

import java.util.Optional;


public class VideoProcesor {

    private final SpechRecognitionGenerator spechRecognitionGenerator;
    private final VideoRepository videoRepository;

    public VideoProcesor(SpechRecognitionGenerator spechRecognitionGenerator,
                         VideoRepository videoRepository) {
        this.spechRecognitionGenerator = spechRecognitionGenerator;
        this.videoRepository = videoRepository;
    }

    public InterviewText getTextFromVideo(String videoName) {


        Optional<VideoTest> videoTestOptional = videoRepository.getVideoByName(videoName);

        if (videoTestOptional.isEmpty()) {
            throw new RuntimeException("Video does not exist");
        }


        VideoTest videoTest = videoTestOptional.get();

        InterviewText textFromVideo =  new InterviewText();

        try {
            textFromVideo = spechRecognitionGenerator.generateTextFromVideo(videoTest);
        }catch ( Exception e) {
            e.printStackTrace();
        }


        return textFromVideo;

    }




}
