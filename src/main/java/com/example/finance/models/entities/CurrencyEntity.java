package com.example.finance.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Currency")
public class CurrencyEntity {

    @Id
    @Column(unique = true)
    private Integer code;

    @Column
    private String name;

    @Column
    private String description;

}
