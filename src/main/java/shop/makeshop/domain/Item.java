package shop.makeshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String itemName;
    private int price;
    private int quantity;

    public void updateItem(ItemUpdateDto updateParam) {
        this.itemName = updateParam.getItemName();
        this.price = updateParam.getPrice();
        this.quantity = updateParam.getQuantity();
    }

    public Item(String itemName, int price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
