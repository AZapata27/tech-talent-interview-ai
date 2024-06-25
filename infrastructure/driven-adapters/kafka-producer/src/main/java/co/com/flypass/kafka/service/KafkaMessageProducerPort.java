package co.com.flypass.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerPort implements IMessageProducerPort {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessageProducerPort(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topicName,Object message) {
        kafkaTemplate.send(topicName, message);
    }
}
