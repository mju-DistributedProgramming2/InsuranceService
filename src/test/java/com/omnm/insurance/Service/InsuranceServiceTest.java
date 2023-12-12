package com.omnm.insurance.Service;

import com.omnm.insurance.DAO.InsuranceDAO;
import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.enumeration.insurance.InsuranceStatus;
import com.omnm.insurance.enumeration.insurance.InsuranceType;
import com.omnm.insurance.Entity.Insurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsuranceServiceTest {

    @Mock
    private InsuranceDAO insuranceDAO;

    @InjectMocks
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInsuranceList() {
        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(new Insurance("Health Insurance",
                InsuranceType.WorkplaceFire,
                "aakk",
                1,
                "Compensation conditions",
                "Not provided",
                InsuranceStatus.UnderAuthorize));

        when(insuranceDAO.findInsurance()).thenReturn(insuranceList);

        ResponseEntity<InsuranceList> response = insuranceService.getInsuranceList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new InsuranceList(insuranceList), response.getBody());

        verify(insuranceDAO, times(1)).findInsurance();
        verifyNoMoreInteractions(insuranceDAO);
    }

    @Test
    void testGetInsuranceListByInsuranceStatus() {
        InsuranceStatus insuranceStatus = InsuranceStatus.UnderAuthorize;
        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(new Insurance("Health Insurance",
                InsuranceType.WorkplaceFire,
                "aakk",
                1,
                "Compensation conditions",
                "Not provided",
                InsuranceStatus.UnderAuthorize));

        when(insuranceDAO.findByStatus(insuranceStatus)).thenReturn(insuranceList);

        ResponseEntity<InsuranceList> response = insuranceService.getInsuranceListByInsuranceStatus(insuranceStatus);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new InsuranceList(insuranceList), response.getBody());

        verify(insuranceDAO, times(1)).findByStatus(insuranceStatus);
        verifyNoMoreInteractions(insuranceDAO);
    }
}
