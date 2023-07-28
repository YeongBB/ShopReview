package shop.makeshop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.makeshop.domain.Item;
import shop.makeshop.domain.ItemUpdateDto;
import shop.makeshop.service.ItemService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemService itemService;

    @Test
    void save(){
        //given
        Item item = new Item("ItemA", 10000, 10);

        //when
        Long joinId = itemService.join(item);

        //then
        Item findItem = itemService.findById(item.getId()).get();
        assertThat(item).isEqualTo(findItem);
    }

    @Test
    void update(){
        //given
        Item item = new Item("ItemA", 10000, 10);
        Long joinId = itemService.join(item);

        //when
        itemService.update(joinId,new ItemUpdateDto("UpdateItem", 20000, 20));

        //then
        Item findItem = itemService.findById(joinId).orElse(null);
        assertThat("UpdateItem").isEqualTo(findItem.getItemName());
        assertThat(20000).isEqualTo(findItem.getPrice());
        assertThat(20).isEqualTo(findItem.getQuantity());
    }

    @Test
    void delete(){
        //given
        Item item = new Item("ItemA", 10000, 10);
        Long joinId = itemService.join(item);
        List<Item> prevList = itemService.list(joinId);

        assertThat(prevList.size()).isEqualTo(1);

        //when
        itemService.delete(joinId);

        //then
        List<Item> postList = itemService.list(joinId);
        assertThat(postList.size()).isEqualTo(0);
    }
}