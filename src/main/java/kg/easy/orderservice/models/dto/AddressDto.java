package kg.easy.orderservice.models.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String street;
    private String house;
    private String flat;
    private String floor;
    private String intercom;
    private String description;

    private boolean active;
    private boolean main;
}
