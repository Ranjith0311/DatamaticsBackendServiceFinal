package com.Datamatics.ranjithmg.backend.controller;

import com.Datamatics.ranjithmg.backend.model.Company;
import com.Datamatics.ranjithmg.backend.model.DataDemo;
import com.Datamatics.ranjithmg.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
public class DataContoller {

    @Autowired
    private DataService dataService;

    @GetMapping("/getData")
    public List<Company> getData(){
        return dataService.getData();
    }

    @GetMapping("/getAllData/{i}")
    public List<DataDemo> getAllData(@PathVariable long i){
        return dataService.getAllData(i);
    }

    @PostMapping("/getRequestedData")
    public List<DataDemo> getRequestedData(@Valid @RequestBody DataDemo dataDemo){
        return dataService.getRequestedData(dataDemo);
    }
}
