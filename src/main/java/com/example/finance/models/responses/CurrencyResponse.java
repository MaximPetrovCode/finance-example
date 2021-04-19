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
public class CurrencyResponse {
    @NotNull
    private Integer code;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
