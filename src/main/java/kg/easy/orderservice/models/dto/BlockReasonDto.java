package kg.easy.orderservice.models.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class BlockReasonDto {

    private Long id;

    private String reason;

    private boolean visible;
}
