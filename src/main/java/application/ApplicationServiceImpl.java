package application;

import domain.Client;
import domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    private ClientRepository repository;


    @Autowired
    public ApplicationServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getClientsWithEvenId() {

        List<Client> list = repository.getClients();

        return list.stream().filter(client -> client.getId() % 2 == 0).collect(Collectors.toList());

    }

    @Override
    public void addWordForEntitiesThatBeginsWithE() {

        List<Client> list = repository.getClients();

        list.stream().filter(client -> client.getName().charAt(0) == 'E')
                .forEach(client -> {
                    client.setName(client.getName() + "_З");
                    repository.editClient(client);
                });
    }




}
