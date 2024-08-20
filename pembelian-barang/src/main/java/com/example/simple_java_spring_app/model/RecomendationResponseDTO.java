package com.example.simple_java_spring_app.model;

import com.example.simple_java_spring_app.entity.Item;
import com.example.simple_java_spring_app.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecomendationResponseDTO {
    private Long totalPrice;
    private Long remainingBudget;
    private List<Item> itemList;
}
