package co.com.flypass.jpa.mysql.query.adapters;


import co.com.flypass.jpa.mysql.query.mappers.IClientEntityQueryMapper;
import co.com.flypass.jpa.mysql.query.repositories.ClientMySqlRepository;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryQueryPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional("mysqlTransactionManager")
public class ClientMySqlAdapterQuery implements ClientRepositoryQueryPort {
    private final ClientMySqlRepository clientMySqlRepository;
    private final IClientEntityQueryMapper clientEntityMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "cliente";

    public ClientMySqlAdapterQuery(ClientMySqlRepository clientMySqlRepository, IClientEntityQueryMapper clientEntityMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.clientMySqlRepository = clientMySqlRepository;
        this.clientEntityMapper = clientEntityMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public List<Client> findClientById(Long clientIdentificationNumber) {
        kafkaTemplate.send(TOPIC, "Buscando cliente con el numero de identificacion: " + clientIdentificationNumber);
        return clientMySqlRepository.findByIdentificationNumber(clientIdentificationNumber).stream().map(clientEntityMapper::toClient).toList();
    }
}
