package com.grofers.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotNull(message = "Supplier ID is mandatory")
	@ManyToOne
	@JoinColumn(name = "supplierId", insertable = false, updatable = false)
	private Supplier supplier;

	@NotNull(message = "Category ID is mandatory")
	@ManyToOne
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category category;

	@NotNull(message = "Price is mandatory")
	@Min(value = 0, message = "Price must be positive")
	private Double price;
}
