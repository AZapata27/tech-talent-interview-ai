package co.com.flypass.ports.outbound;

public interface IMessageProducerPort {
    void sendMessage(String topicName,Object message);
}
