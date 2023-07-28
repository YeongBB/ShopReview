package shop.makeshop.domain;

import lombok.Getter;

@Getter
public class ItemUpdateDto {

    private String itemName;
    private int price;
    private int quantity;

    public ItemUpdateDto(String itemName, int price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
