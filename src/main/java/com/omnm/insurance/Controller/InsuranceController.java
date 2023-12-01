package com.omnm.insurance.Controller;


import com.omnm.insurance.Entity.Insurance;
import com.omnm.insurance.enumeration.insurance.InsuranceStatus;
import com.omnm.insurance.Service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class InsuranceController {
    @Autowired
    InsuranceService insuranceService;
    @GetMapping("/list/insurances")
    public ResponseEntity<List<Insurance>> getInsuranceList() {
        return this.insuranceService.getInsuranceList();
    }
    @GetMapping("/insurances")
    public ResponseEntity<Insurance> getInsuranceById(Integer selectedInsuranceId) {
        return this.insuranceService.getInsuranceById(selectedInsuranceId);
    }
    @PostMapping("/insurances")
    public ResponseEntity<Integer> postInsurance(@RequestBody Insurance insurance) {
        return this.insuranceService.postInsurance(insurance);
    }
    @PostMapping("/authorization")
    public ResponseEntity<Boolean> patchInsuranceStatusInInsuranceById(@RequestBody HashMap<String, String> param) {
        Integer id = Integer.valueOf(param.get("id"));
        InsuranceStatus status = InsuranceStatus.valueOf(param.get("status"));
        return this.insuranceService.patchInsuranceStatusInInsuranceById(id, status);
    }

    @GetMapping("/check-name")
    public ResponseEntity<Boolean> getInsuranceByName(String name) {
        return this.insuranceService.getInsuranceByName(name);
    }
}
