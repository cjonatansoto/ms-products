package com.ing.cjonatansoto.ms.web.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ing.cjonatansoto.ms.common.constants.CoreConstants;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    @JsonFormat(pattern = CoreConstants.DATE_FORMAT_DDMMYYYY_HHMMSS)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = CoreConstants.DATE_FORMAT_DDMMYYYY_HHMMSS)
    private LocalDateTime updatedAt;
}
