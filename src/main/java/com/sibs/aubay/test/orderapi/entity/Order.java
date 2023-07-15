package com.sibs.aubay.test.orderapi.entity;

import javax.persistence.*;

import com.sibs.aubay.test.orderapi.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@NoArgsConstructor
public class Order {

    public Order(int id, Item item, int quantity, User user){
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    @Getter @Setter
    private Item item;

    @Column(name = "quantity")
    @Getter @Setter
    private int quantity;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @Getter @Setter
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", user=" + user +
                '}';
    }

}
