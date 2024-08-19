package com.example.simple_java_spring_app.controller;

import com.example.simple_java_spring_app.entity.EggLaidRecord;
import com.example.simple_java_spring_app.model.AddEggLaidRecordDTO;
import com.example.simple_java_spring_app.model.EggLaidRecapitulationReport;
import com.example.simple_java_spring_app.service.EggLaidRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class EggLaidRecordController {

    @Autowired
    private EggLaidRecordService eggLaidRecordService;

    @PostMapping()
    public ResponseEntity<EggLaidRecord> addNewRecord(@RequestBody AddEggLaidRecordDTO request){
        EggLaidRecord record;
        try {
            record = eggLaidRecordService.addRecord(request);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(record);
    }

    @GetMapping("/recapitulation")
    public ResponseEntity<List<EggLaidRecapitulationReport>> getRecapitulation(){
        return ResponseEntity.ok(eggLaidRecordService.getRecapitulationPerHenHouse());
    }

    @GetMapping()
    public ResponseEntity<List<EggLaidRecord>> getAllRecord(){
        return ResponseEntity.ok(eggLaidRecordService.getAllEggLaidRecord());
    }
}
