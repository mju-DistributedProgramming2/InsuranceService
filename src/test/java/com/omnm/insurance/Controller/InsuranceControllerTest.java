package com.omnm.insurance.Controller;

import com.omnm.insurance.DTO.GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest;
import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.Entity.Insurance;
import com.omnm.insurance.enumeration.insurance.InsuranceStatus;
import com.omnm.insurance.Service.InsuranceService;
import com.omnm.insurance.enumeration.insurance.InsuranceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsuranceControllerTest {

    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceController insuranceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInsuranceList() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceList()).thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceList();
        verifyNoMoreInteractions(insuranceService);
    }

    @Test
    void testGetInsuranceListByInsuranceStatus() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        InsuranceStatus insuranceStatus = InsuranceStatus.RefuseAuthorize;
        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceListByInsuranceStatus(insuranceStatus))
                .thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceListByInsuranceStatus(insuranceStatus);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceListByInsuranceStatus(insuranceStatus);
        verifyNoMoreInteractions(insuranceService);
    }

    @Test
    void testGetInsuranceListByInsuranceTypeAndInsuranceStatus() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest request = new GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest(
                InsuranceType.HomeFire, InsuranceStatus.UnderAuthorize);

        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceListByInsuranceTypeAndInsuranceStatus(request.getType(), request.getStatus()))
                .thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceListByInsuranceTypeAndInsuranceStatus(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceListByInsuranceTypeAndInsuranceStatus(request.getType(), request.getStatus());
        verifyNoMoreInteractions(insuranceService);
    }
}
