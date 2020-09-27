package com.Datamatics.ranjithmg.backend.controller;

import com.Datamatics.ranjithmg.backend.model.DataDemo;
import com.Datamatics.ranjithmg.backend.model.DataRequest;
import com.Datamatics.ranjithmg.backend.service.ExcludeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
public class ExcludeController {

    @Autowired
    private ExcludeService excludeService;

    @PostMapping("/getRequestedData/exclude")
    public List<DataDemo> getRequestedData(@Valid @RequestBody DataRequest dataRequest){
        return excludeService.getExcludedData(dataRequest);
    }
}
