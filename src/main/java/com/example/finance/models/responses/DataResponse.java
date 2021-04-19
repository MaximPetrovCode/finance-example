package com.example.finance.models.responses;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DataResponse {
    @NotNull
    private Integer id;

    @NotNull
    private Integer currencyCode;

    @NotNull
    private String currencyName;

    @NotNull
    private String currencyDescription;

    @NotNull
    private Integer nums;

    @NotNull
    private Double course;

    @NotNull
    private Long timestamp;
}
