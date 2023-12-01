package com.omnm.insurance.Service;

import com.omnm.insurance.enumeration.insurance.InsuranceType;
import com.omnm.insurance.enumeration.insurance.InsuranceStatus;
import com.omnm.insurance.Entity.Insurance;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsuranceServiceIF {

    ResponseEntity<List<Insurance>> getInsuranceList();

    ResponseEntity<List<Insurance>> getInsuranceListByInsuranceStatus(InsuranceStatus insuranceStatus);

    ResponseEntity<List<Insurance>> getInsuranceListByInsuranceTypeAndInsuranceStatus(InsuranceType type, InsuranceStatus status);

    ResponseEntity<Insurance> getInsuranceById(Integer selectedInsuranceId);

    ResponseEntity<Integer> postInsurance(Insurance insurance);

    ResponseEntity<Boolean> patchInsuranceStatusInInsuranceById(Integer id, InsuranceStatus status);

    ResponseEntity<Boolean> getInsuranceByName(String name);
}
