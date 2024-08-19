package com.example.simple_java_spring_app.service;

import com.example.simple_java_spring_app.entity.EggLaidRecord;
import com.example.simple_java_spring_app.entity.HenHouse;
import com.example.simple_java_spring_app.model.AddEggLaidRecordDTO;
import com.example.simple_java_spring_app.model.EggLaidRecapitulationReport;
import com.example.simple_java_spring_app.repository.EggLaidRecordRepository;
import com.example.simple_java_spring_app.repository.HenHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EggLaidRecordService {

    @Autowired
    private EggLaidRecordRepository eggLaidRecordRepository;

    @Autowired
    private HenHouseRepository henHouseRepository;

    public EggLaidRecord addRecord(AddEggLaidRecordDTO addEggLaidRecordDTO){
        Optional<HenHouse> henHouseOptional = henHouseRepository.findById(addEggLaidRecordDTO.getHenhouseId());

        if(henHouseOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hen house id is not valid");
        }

        EggLaidRecord newRecord = new EggLaidRecord();
        newRecord.setRecordDate(new Date());
        newRecord.setAmount(addEggLaidRecordDTO.getAmount());
        newRecord.setHenHouse(henHouseOptional.get());

        return eggLaidRecordRepository.save(newRecord);
    }

    public List<EggLaidRecapitulationReport> getRecapitulationPerHenHouse(){
        return eggLaidRecordRepository.findRecapitulationGroupByHenHouse();
    }

    public List<EggLaidRecord> getAllEggLaidRecord(){
        return eggLaidRecordRepository.findAll();
    }
}
