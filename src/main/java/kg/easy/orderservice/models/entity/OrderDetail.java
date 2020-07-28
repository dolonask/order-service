package kg.easy.orderservice.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Client recipient;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Client sender;

    @ManyToOne
    @JoinColumn(name = "from_address_id")
    private Address fromAddress;

    @ManyToOne
    @JoinColumn(name = "to_address_id")
    private Address toAddress;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Bishkek")
    private Date estimatedDate;
}
