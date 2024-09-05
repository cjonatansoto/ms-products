package com.ing.cjonatansoto.ms.web.requests;

import jakarta.validation.constraints.Min;
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
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    @Min(1)
    private BigDecimal price;
}
