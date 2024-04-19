package co.com.flypass.kafka.workers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = Logger.getLogger(KafkaConsumer.class.getName());
    @KafkaListener(topics = "${kafka.topic.cliente}", groupId = "${kafka.consumer.backendArquetipoGroupId}")
    public void listenClientCreated(String message) {
        LOGGER.info("Mensaje recibido del topic 'cliente': " + message);
    }
}
