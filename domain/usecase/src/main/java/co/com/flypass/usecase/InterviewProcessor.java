package co.com.flypass.usecase;

import co.com.flypass.models.*;
import co.com.flypass.ports.outbound.InterviewEvaluator;
import co.com.flypass.ports.outbound.InterviewRepository;

public class InterviewProcessor {

    private final VideoProcesor videoProcesor;
    private final InterviewEvaluator interviewEvaluator;
    private final InterviewRepository interviewRepository;


    public InterviewProcessor(VideoProcesor videoProcesor,
                              InterviewEvaluator interviewEvaluator,
                              InterviewRepository interviewRepository) {
        this.videoProcesor = videoProcesor;
        this.interviewEvaluator = interviewEvaluator;
        this.interviewRepository = interviewRepository;
    }


    public void evaluateVideo(String videoName){

        InterviewText interviewText = videoProcesor.getTextFromVideo(videoName);

        EvaluationResults evaluationResults = interviewEvaluator.evaluate(interviewText);

        Interview interview = new Interview(evaluationResults);

        interviewRepository.save(interview);

    }
}
