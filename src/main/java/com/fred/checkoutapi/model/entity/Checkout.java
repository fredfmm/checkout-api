package com.fred.checkoutapi.model.entity;

import com.fred.checkoutapi.model.enums.CheckoutStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Checkout extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private CheckoutStatus status;

	private UUID productId;

	private Integer quantity;

	private String customerName;

	private String customerEmail;

	private String deliveryAddress;

}
