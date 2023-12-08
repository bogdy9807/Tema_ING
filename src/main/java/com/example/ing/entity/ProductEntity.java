package com.example.ing.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "PRODUCT")
@Data
public class ProductEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 7953739569488702623L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "S_PRODUCT_0", allocationSize = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EXTERNAL_ID")
	private String externalId;

	@Column(name = "PRICE")
	private Float price;

	@Column(name = "CREATED_AT")
	private ZonedDateTime createdAt;
}
