package com.sibs.aubay.test.orderapi.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="stock_movement")
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter
    private int id;

    @Column(name = "creation_date")
    @Getter @Setter
    private long creationDate;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    @Getter @Setter
    private Item item;

    @Column(name = "quantity")
    @Getter @Setter
    private int quantity;

}
