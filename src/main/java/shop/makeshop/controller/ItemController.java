package shop.makeshop.controller;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.makeshop.domain.Item;
import shop.makeshop.domain.ItemUpdateDto;
import shop.makeshop.service.ItemService;

@Service
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(){
        return "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, @ModelAttribute("item") Item item){
        item = itemService.findById(itemId).orElse(null);
        return "item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Long join = itemService.join(item);
        item = itemService.findById(join).get();
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, @ModelAttribute Item item){
        item = itemService.findById(itemId).orElse(null);
        return "editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateDto){
        itemService.update(itemId, updateDto);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String delete(@PathVariable long itemId){
        itemService.delete(itemId);
        return "items";
    }

}
