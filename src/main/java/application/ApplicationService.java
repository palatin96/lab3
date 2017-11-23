package application;

import domain.Client;

import java.util.List;

public interface ApplicationService {

    List<Client> getClientsWithEvenId();

    void addWordForEntitiesThatBeginsWithE();

}
