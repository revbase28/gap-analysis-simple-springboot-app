package com.example.simple_java_spring_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "egg_laid_record")
public class EggLaidRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private Integer amount;

    @Column(nullable = false)
    private Date recordDate;

    @ManyToOne
    @JoinColumn(name = "henhouse_id", referencedColumnName = "id")
    private HenHouse henHouse;
}
