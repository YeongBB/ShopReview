package shop.makeshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.makeshop.domain.Item;
import shop.makeshop.domain.ItemUpdateDto;
import shop.makeshop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //저장
    @Transactional
    public Long join(Item item){
        Item save = itemRepository.save(item);
        return save.getId();
    }

    // 읽기
    public List<Item> list(Long id){
        return itemRepository.findAll();
    }

    // 내 아이디 찾기
    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    //업데이트
    @Transactional
    public void update(Long id, ItemUpdateDto updateParam){
        Item item = findById(id).get();
        item.updateItem(updateParam);
    }

    //삭제
    @Transactional
    public void delete(Long id){
        itemRepository.deleteById(id);
    }




}
