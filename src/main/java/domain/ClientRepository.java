package domain;

import java.util.List;

public interface ClientRepository {

    Client getEntity(long id);

    void addClient(Client client);

    void editClient(Client entity);

    void removeEntity(Client entity);

    List getClients();


}
