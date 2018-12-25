package com.tdat.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.MasterData;
import com.tdat.data.MockData;
import com.tdat.data.VisitData;
import com.tdat.data.analysis.ServiceReceivedVerifier;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ServiceReceivedVerifierTest {

    @BeforeAll
    public static void setUp(){
        MockData.populateServiceProvidedData();
        MockData.populateInitialVisitData();
    }

    @Test
    void getReferralsCount() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Employment-related Referrals", 1);
        expected.put("Financial Referrals", 1);
        Map<String, Integer> actual = ServiceReceivedVerifier.getReferralsCount();
        assertEquals(expected, actual);
    }

    @Test
    void checkAllServicesReceived() {
        int expected = 1;
        Map<String, Integer> actual = ServiceReceivedVerifier.checkAllServicesReceived();
        assertEquals((Integer) expected, actual.get("Financial Referrals"));
    }

    @Test
    void checkIndivServicesReceived() throws ColumnNotFoundException {
        VisitData person = MasterData.initialVisitData.getVisitsData().get(0);
        Map<String, Boolean> actual = ServiceReceivedVerifier.checkIndivServicesReceived(person);
        assertTrue(actual.get("Financial Referrals"));
    }

    @AfterAll
    static void cleanUp(){
        MasterData.clear();
    }
}