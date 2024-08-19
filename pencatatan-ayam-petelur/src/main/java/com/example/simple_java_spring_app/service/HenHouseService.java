package com.example.simple_java_spring_app.service;

import com.example.simple_java_spring_app.entity.HenHouse;
import com.example.simple_java_spring_app.model.AddHenHouseDTO;
import com.example.simple_java_spring_app.repository.HenHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HenHouseService {

    @Autowired
    private HenHouseRepository henHouseRepository;

    public HenHouse addHenHouse(AddHenHouseDTO addHenHouseDTO){
        HenHouse henHouse = new HenHouse();
        henHouse.setAddress(addHenHouseDTO.getAddress());

        return henHouseRepository.save(henHouse);
    }

    public List<HenHouse> getAllHenHouses(){
        return henHouseRepository.findAll();
    }
}
