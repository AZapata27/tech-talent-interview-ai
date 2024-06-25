package co.com.flypass.ports.outbound;

import co.com.flypass.models.EvaluationResults;
import co.com.flypass.models.InterviewText;

public interface InterviewEvaluator {
    EvaluationResults evaluate(InterviewText interviewText);
}
