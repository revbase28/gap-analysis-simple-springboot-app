package com.example.simple_java_spring_app.controller;

import com.example.simple_java_spring_app.entity.Item;
import com.example.simple_java_spring_app.model.AddItemDTO;
import com.example.simple_java_spring_app.model.RecomendationResponseDTO;
import com.example.simple_java_spring_app.model.WebResponse;
import com.example.simple_java_spring_app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<Item> addNewItem(@RequestBody AddItemDTO requestData){
        return ResponseEntity.ok(itemService.addNewItem(requestData));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getAllItem(){
        return ResponseEntity.ok(itemService.getAllItem());
    }

    @GetMapping("/purchase-recommendation/{budget}")
    public ResponseEntity<WebResponse<RecomendationResponseDTO>> getItemPurchaseRecommendation(@PathVariable("budget") Long budget){
        RecomendationResponseDTO recomendation = itemService.getItemPurchaseSuggestion(budget);
        if(recomendation.getItemList().size() == 0){
            return ResponseEntity.ok(WebResponse.<RecomendationResponseDTO>builder()
                    .message("There are no purchase recommendations for a budget of " +
                            budget + " maybe your budget is too small")
                    .data(null).build());
        }
        return ResponseEntity.ok(WebResponse.<RecomendationResponseDTO>builder()
                .data(recomendation).build());
    }
}
