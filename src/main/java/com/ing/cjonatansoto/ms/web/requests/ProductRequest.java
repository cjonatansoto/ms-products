package com.ing.cjonatansoto.ms.web.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest implements Serializable {
    @NotEmpty(message = "El nombre no puede estar vacío.")
    @NotNull(message = "El nombre no puede ser nulo.")
    private String name;
    @NotNull(message = "El precio no puede ser nulo.")
    private BigDecimal price;
}
