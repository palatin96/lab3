package domain;

import javax.persistence.*;

@Entity
@NamedQuery(name="Client.findAll", query="SELECT t FROM Client t order by t.id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    public Client() {}

    public Client(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Client(long id, String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        Client client = (Client) obj;

        return getName().equals(client.getName()) && getId() == client.getId()
                && getAddress().equals(client.getAddress()) && getEmail().equals(client.getEmail()) && getPhone().equals(client.getPhone());
    }
}
