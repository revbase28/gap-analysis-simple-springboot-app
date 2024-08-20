package com.example.simple_java_spring_app.entity;

import com.example.simple_java_spring_app.tools.Group;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Group itemGroup;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Long price;

}
