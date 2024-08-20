package com.example.simple_java_spring_app.model;

import com.example.simple_java_spring_app.tools.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddItemDTO {

    private Group group;

    private String itemName;

    private Long price;
}
