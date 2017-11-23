package domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class ClientRepositoryImpl implements ClientRepository {


    @PersistenceUnit(name = "manager")
    private EntityManager manager;


    @Override
    public Client getEntity(long id) {
        return manager.find(Client.class, id);
    }

    @Override
    public void editClient(Client entity) {
        manager.merge(entity);
    }


    public void addClient(Client client) {
        manager.persist(client);
        manager.flush();
    }

    @Override
    public void removeEntity(Client entity) {

        entity = manager.merge(entity);
        manager.remove(entity);
    }

    @Override
    public List<Client> getClients() {
        Query query = manager.createNamedQuery("Client.findAll", Client.class);
        return query.getResultList();
    }




}
