package co.com.flypass.ports.outbound;

import co.com.flypass.models.VideoTest;

import java.util.Optional;

public interface VideoRepository {
    Optional<VideoTest> getVideoByName(String videoName);
}
