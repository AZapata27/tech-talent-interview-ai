package co.com.flypass.config;


import co.com.flypass.ports.outbound.ClientRepositoryCommandPort;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.ports.outbound.ClientRepositoryQueryPort;
import co.com.flypass.usecase.ClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
        @Bean
        public ClientUseCasePort clientUseCase(ClientRepositoryCommandPort clientCommand, ClientRepositoryQueryPort clientQuery){
                return new ClientUseCase(clientCommand, clientQuery);
        }
}
