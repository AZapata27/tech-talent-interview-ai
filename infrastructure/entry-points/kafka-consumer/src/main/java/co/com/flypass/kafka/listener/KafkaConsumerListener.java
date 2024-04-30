package co.com.flypass.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class KafkaConsumerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = "${kafka.topic.cliente}", groupId = "${kafka.consumer.backendArquetipoGroupId}")
    public void listenClientCreated(String message) {
        LOGGER.info("Mensaje recibido del topic 'cliente': " + message);
    }
}
