package com.example.simple_java_spring_app.model;

import com.example.simple_java_spring_app.entity.HenHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EggLaidRecapitulationReport {
    private Integer henHouseId;
    private String henHouseAddress;
    private Integer totalEggLaid;
}
