package com.Datamatics.ranjithmg.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class DataRequest {
    private String name;
    private String country;
    private String jobFunction;
    private String jobLevel;
    private String subIndustry;
    private String company;
}
