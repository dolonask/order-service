package kg.easy.orderservice.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String msisdn;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
