package com.tdat.data;

import com.tdat.data.analysis.ServiceReceivedVerifier;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to populate MasterData with dummy data
 */
public class MockData {
    public static void populateServiceProvidedData(){
        List<Map<String, String>> mockData = new ArrayList<>();
        Map<String, String> visit1 = new HashMap<>();
        Map<String, String> visit2 = new HashMap<>();
        visit1.put(ServiceReceivedVerifier.UID, "1");
        visit1.put("Col2", "a");
        visit1.put("Template", ServiceReceivedVerifier.COMMUNITY);
        visit2.put(ServiceReceivedVerifier.UID, "2");
        visit2.put("Col2", "b");
        visit2.put("Template", ServiceReceivedVerifier.INFO);
        mockData.addAll(Arrays.asList(visit1, visit2));
        MasterData.setServiceProvidedData(Year.of(2018), mockData);
        MasterData.setServiceProvidedData(Year.of(2017), mockData);
    }

    public static void populateInitialVisitData(){
        List<Map<String, String>> mockData = new ArrayList<>();
        Map<String, String> visit1 = new HashMap<>();
        Map<String, String> visit2 = new HashMap<>();
        visit1.put(ServiceReceivedVerifier.UID, "1");
        visit1.put("Col2", "a");
        visit1.put("Financial Referrals", "Yes");
        visit2.put(ServiceReceivedVerifier.UID, "2");
        visit2.put("Col2", "b");
        visit2.put("Employment-related Referrals", "Yes");
        mockData.addAll(Arrays.asList(visit1, visit2));
        MasterData.setInitialVisitData(mockData);
    }


}
