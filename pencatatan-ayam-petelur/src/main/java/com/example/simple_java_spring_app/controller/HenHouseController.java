package com.example.simple_java_spring_app.controller;

import com.example.simple_java_spring_app.entity.HenHouse;
import com.example.simple_java_spring_app.model.AddHenHouseDTO;
import com.example.simple_java_spring_app.service.HenHouseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/henhouse")
public class HenHouseController {

    @Autowired
    private HenHouseService henHouseService;

    @PostMapping()
    public ResponseEntity<HenHouse> addNewHenHouse(@RequestBody AddHenHouseDTO request){
        HenHouse henHouse = henHouseService.addHenHouse(request);
        return ResponseEntity.ok(henHouse);
    }

    @GetMapping()
    public ResponseEntity<List<HenHouse>> getAllHenHouse(){
        List<HenHouse> henHouses = henHouseService.getAllHenHouses();
        return ResponseEntity.ok(henHouses);
    }
}
