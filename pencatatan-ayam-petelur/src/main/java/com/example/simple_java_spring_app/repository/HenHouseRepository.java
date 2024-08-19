package com.example.simple_java_spring_app.repository;

import com.example.simple_java_spring_app.entity.HenHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HenHouseRepository extends JpaRepository<HenHouse, Integer> {
}
