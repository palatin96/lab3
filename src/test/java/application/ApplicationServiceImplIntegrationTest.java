package application;

import application.ApplicationServiceImpl;
import domain.Client;
import domain.ClientRepository;
import domain.ClientRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class ApplicationServiceImplIntegrationTest {



    @Autowired
    public ApplicationService app;

    @Autowired
    public ClientRepository repository;

    private List<Client> dataBaseTestEntities;

    public ApplicationServiceImplIntegrationTest() {

        dataBaseTestEntities = new ArrayList<>();
        dataBaseTestEntities.add(new Client(1,"Ekaterina", "Address", "email", "phone"));
        dataBaseTestEntities.add(new Client(2,"Petya", "Address", "email", "phone"));
        dataBaseTestEntities.add(new Client(3,"Emily", "Address", "email", "phone"));
        dataBaseTestEntities.add(new Client(4, "Vasya", "Address", "email", "phone"));
    }

    @Before
    public void initDataBase() {


        for (Client client: dataBaseTestEntities) {
            repository.addClient(client);
        }
    }

    @After
    public void rollBackDataBase() {
        for (Client client: dataBaseTestEntities) {
            repository.removeEntity(client);
        }
    }



    @Test
    public void getClientsWithEvenId() {

        List<Client> exp = new ArrayList<>();
        exp.add(new Client(2, "Petya", "Address", "email", "phone"));
        exp.add(new Client(4, "Vasya", "Address", "email", "phone"));

        assertEquals(exp, app.getClientsWithEvenId());
    }

    @Test
    public void addWordForEntitiesThatBeginsWithE() {


        List<Client> exp = new ArrayList<>();
        exp.add(new Client(1, "Ekaterina_З", "Address", "email", "phone"));
        exp.add(new Client(2, "Petya", "Address", "email", "phone"));
        exp.add(new Client(3, "Emily_З", "Address", "email", "phone"));
        exp.add(new Client( 4, "Vasya", "Address", "email", "phone"));

        app.addWordForEntitiesThatBeginsWithE();

        List<Client> A = repository.getClients();

        assertEquals(exp, repository.getClients());
    }

}