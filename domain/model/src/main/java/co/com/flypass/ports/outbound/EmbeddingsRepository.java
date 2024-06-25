package co.com.flypass.ports.outbound;

import co.com.flypass.models.EmbeddingInterview;
import co.com.flypass.models.InterviewText;

public interface EmbeddingsRepository {
    EmbeddingInterview saveInterview(InterviewText textInterview);
}
