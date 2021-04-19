package com.example.finance.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Data")
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @ManyToOne(targetEntity = CurrencyEntity.class)
    private CurrencyEntity currency;

    @Column
    private Integer nums;

    @Column
    private Double course;

    @Column
    private Long timestamp;

}
