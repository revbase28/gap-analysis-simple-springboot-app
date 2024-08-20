package com.example.simple_java_spring_app.service;

import com.example.simple_java_spring_app.entity.Item;
import com.example.simple_java_spring_app.model.AddItemDTO;
import com.example.simple_java_spring_app.model.RecomendationResponseDTO;
import com.example.simple_java_spring_app.repository.ItemRepository;
import com.example.simple_java_spring_app.tools.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addNewItem(AddItemDTO addItemDTO){
        Item item = Item.builder()
                .itemName(addItemDTO.getItemName())
                .price(addItemDTO.getPrice())
                .itemGroup(addItemDTO.getGroup()).build();

        return itemRepository.save(item);
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public RecomendationResponseDTO getItemPurchaseSuggestion(long budget){
        long remainingBudget = budget;

        List<Item> items = itemRepository.findAll();
        ArrayList<Item> result = new ArrayList<>();

        // Sort item by price
        items.sort(Comparator.comparingLong(Item::getPrice).reversed());

        // If budget cannot afford the cheapest item, return empty list
        if(remainingBudget < items.get(items.size() - 1).getPrice()){
            return RecomendationResponseDTO.builder()
                    .itemList(result)
                    .remainingBudget(remainingBudget)
                    .totalPrice(0L).build();
        }

        // Add the most expensive item possible
        for(Item item: items){
            while(remainingBudget >= item.getPrice()){
                result.add(item);
                remainingBudget -= item.getPrice();
            }
        }

        // Make sure there is at least one mouse and one keyboard in result
        boolean hasMouse = result.stream().anyMatch(item -> item.getItemGroup().equals(Group.MOUSE));
        boolean hasKeyboard = result.stream().anyMatch(item -> item.getItemGroup().equals(Group.KEYBOARD));

        if(!hasMouse || !hasKeyboard){
            // Reset
            result.clear();
            remainingBudget = budget;

            //Find the cheapest keyboard and mouse
            Item cheapestMouse = items.stream().filter(item -> item.getItemGroup().equals(Group.MOUSE))
                    .min(Comparator.comparingLong(Item::getPrice)).orElse(null);

            Item cheapestKeyboard = items.stream().filter(item -> item.getItemGroup().equals(Group.KEYBOARD))
                    .min(Comparator.comparingLong(Item::getPrice)).orElse(null);

            if(cheapestMouse != null && cheapestKeyboard != null){

                // Check if remaining budget can buy both of the cheapest item
                if(remainingBudget >= (cheapestKeyboard.getPrice() + cheapestMouse.getPrice())){
                    result.add(cheapestMouse);
                    result.add(cheapestKeyboard);
                    remainingBudget = remainingBudget - cheapestMouse.getPrice() - cheapestKeyboard.getPrice();
                } else {
                    // If Not select most expensive between the two
                    result.add(cheapestMouse.getPrice() >= cheapestKeyboard.getPrice() ? cheapestMouse : cheapestKeyboard);
                }
            }

            for (Item item : items) {
                while (remainingBudget >= item.getPrice()) {
                    result.add(item);
                    remainingBudget -= item.getPrice();
                }
            }
        }

        return RecomendationResponseDTO.builder()
                .totalPrice(budget - remainingBudget)
                .remainingBudget(remainingBudget)
                .itemList(result).build();
    }
}
