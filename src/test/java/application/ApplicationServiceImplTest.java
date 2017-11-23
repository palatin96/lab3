package application;

import domain.Client;
import domain.ClientRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class ApplicationServiceImplTest {

    @Mock
    private ClientRepositoryImpl rep;

    @Autowired
    @InjectMocks
    ApplicationService app;

    @Before
    /* Initialized mocks */
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getClientsWithEvenIdWithENormalValues() {

        List<Client> list = new ArrayList<>();

        list.add(new Client(0, "Vasya", "Address", "email", "phone"));
        list.add(new Client(1, "Ekaterina", "Address", "email", "phone"));
        list.add(new Client(2, "Petya", "Address", "email", "phone"));
        list.add(new Client(3, "Emily", "Address", "email", "phone"));

        List<Client> exp = new ArrayList<>();

        exp.add(new Client(0, "Vasya", "Address", "email", "phone"));
        exp.add(new Client(2, "Petya", "Address", "email", "phone"));

        when(rep.getClients()).thenReturn(list);



        assertEquals(exp, app.getClientsWithEvenId());

        verify(rep).getClients();

    }

    @Test
    public void getClientsWithEvenIdWithEEmptyData() {

        List<Client> list = new ArrayList<>();


        when(rep.getClients()).thenReturn(list);



        assertTrue(app.getClientsWithEvenId().size() == 0);

        verify(rep).getClients();

    }


    @Test
    public void addWordForEntitiesThatBeginsWithENormalValues() {

        List<Client> list = new ArrayList<>();

        list.add(new Client(0, "Vasya", "Address", "email", "phone"));
        list.add(new Client(1, "Ekaterina", "Address", "email", "phone"));
        list.add(new Client(2, "Petya", "Address", "email", "phone"));
        list.add(new Client(3, "Emily", "Address", "email", "phone"));


        when(rep.getClients()).thenReturn(list);

        app.addWordForEntitiesThatBeginsWithE();



        verify(rep).editClient(new Client(1, "Ekaterina_З", "Address", "email", "phone"));

        verify(rep).editClient(new Client(3, "Emily_З", "Address", "email", "phone"));

        verify(rep).getClients();

    }

    @Test
    public void addWordForEntitiesThatBeginsWithENoEntitiesWithE() {

        List<Client> list = new ArrayList<>();

        list.add(new Client(0, "Vasya", "Address", "email", "phone"));
        list.add(new Client(0, "Petya", "Address", "email", "phone"));

        app.addWordForEntitiesThatBeginsWithE();

        verify(rep, never()).editClient(any());

        verify(rep).getClients();

    }


}