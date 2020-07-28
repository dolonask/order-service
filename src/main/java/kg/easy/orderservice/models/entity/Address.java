package kg.easy.orderservice.models.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private String house;
    private String flat;
    private String floor;
    private String intercom;
    private String description;

    private boolean active;
    private boolean main;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
