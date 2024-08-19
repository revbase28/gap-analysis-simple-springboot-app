package com.example.simple_java_spring_app.repository;

import com.example.simple_java_spring_app.entity.EggLaidRecord;
import com.example.simple_java_spring_app.model.EggLaidRecapitulationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EggLaidRecordRepository extends JpaRepository<EggLaidRecord, Integer> {

//    @Query(value = """
//            SELECT new com.example.simple_java_spring_app.model.EggLaidRecapitulationReport(
//            new com.example.simple_java_spring_app.entity.HenHouse(h.id, h.address), SUM(e.amount))
//            FROM EggLaidRecord e
//            JOIN HenHouse h
//            ON e.henHouse.id = h.id
//            GROUP BY e.henHouse.id
//            """)
//    List<EggLaidRecapitulationReport> findRecapitulationGroupByHenHouse();

    @Query(value = """
            SELECT new com.example.simple_java_spring_app.model.EggLaidRecapitulationReport(CAST(h.id AS int), h.address, CAST(SUM(e.amount) AS int))
            FROM EggLaidRecord e
            JOIN HenHouse h
            ON e.henHouse.id = h.id
            GROUP BY e.henHouse.id
            """)
    List<EggLaidRecapitulationReport> findRecapitulationGroupByHenHouse();
}
