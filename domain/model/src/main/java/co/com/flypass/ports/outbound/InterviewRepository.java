package co.com.flypass.ports.outbound;

import co.com.flypass.models.Interview;

public interface InterviewRepository {
    void save(Interview interview);
}
