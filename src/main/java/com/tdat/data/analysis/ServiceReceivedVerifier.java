package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to verify whether users of the agencies are receiving the services they are referred
 */
public class ServiceReceivedVerifier {

    public static final String COMMUNITY = "Community Connections";
    public static final String INFO = "Information and Orientation";
    public static final String EMPLOYMENT = "Employment Related Services";
    public static final String LT_ENROLL = "Language Training - Client Enrolment";
    public static final String UID = "Unique Identifier Value";
    private static final Map<String, String> associations;

    static {
        associations = new HashMap<>();
        associations.put("Community Services Referrals", COMMUNITY);
        associations.put("Education/Skills Development Referrals", COMMUNITY);
        associations.put("Employment-related Referrals", EMPLOYMENT);
        associations.put("Family Support Referrals", COMMUNITY);
        associations.put("Financial Referrals", COMMUNITY);
        associations.put("Find employment Referrals", EMPLOYMENT);
        associations.put("Health/Mental Health/Well Being Referrals", COMMUNITY);
        associations.put("Housing/Accommodation Referrals", COMMUNITY);
        associations.put("Improve Other Skills Referrals", INFO);
        associations.put("Language (Non-IRCC) Referrals", LT_ENROLL);
        associations.put("Legal Information and Services Referrals", INFO);
        associations.put("Improve Language Skills Referrals", LT_ENROLL);
    }

    public static List<String> getAllServices() {
        ArrayList<String> result = new ArrayList<>();
        result.addAll(associations.keySet());
        return result;
    }

    /**
     * Scans initialData, and gets the count for all the services that have been referred
     */
    public static Map<String, Integer> getReferralsCount() {
        Map<String, Integer> result = new HashMap<>();
        List<VisitData> initialVisitData = MasterData.initialVisitData.getVisitsData();

        for (VisitData visit : initialVisitData) {
            for (String referralType : associations.keySet()) {
                String referralStatus = visit.getColumnData(referralType).trim();
                if (referralStatus.equals("Yes")) {
                    if (!result.containsKey(referralType)) {
                        result.put(referralType, 0);
                    }
                    result.put(referralType, result.get(referralType) + 1);
                }
            }
        }
        return result;
    }

    private static boolean wasPersonReferredService(VisitData visit, String service) {
        return visit.getColumnData(service).trim().equals("Yes");
    }


    public static Map<String, Integer> checkAllServicesReceived(){
        Map<String, Integer> result = new HashMap<>();
        for (String service : associations.keySet()) {
            result.put(service, 0);
        }

        for (VisitData visitData : MasterData.initialVisitData.getVisitsData()) {
            Map<String, Boolean> indivServicesReceived;
            try {
                indivServicesReceived = checkIndivServicesReceived(visitData);
            } catch (ColumnNotFoundException e){
                indivServicesReceived = new HashMap<>();
            }
            for (String service : indivServicesReceived.keySet()) {
                if (indivServicesReceived.get(service)) {
                    result.put(service, result.get(service) + 1);
                }
            }
        }
        return result;
    }

    /**
     * Returns map of a service to booleans that represent whether the given person has recieved the
     * data
     */
    public static Map<String, Boolean> checkIndivServicesReceived(VisitData person)
        throws ColumnNotFoundException {
        String currPersonID = person.getColumnData(UID);
        Map<String, Boolean> servicesReceived = new HashMap<>();

        for (String serviceReferred : associations.keySet()) {
            servicesReceived.put(serviceReferred, false);
            if (!wasPersonReferredService(person, serviceReferred)) {
                continue;
            }

            for (Year year : MasterData.serviceProvidedData.keySet()) {
                TableData dataForCurrYear = MasterData.getYearData(year);
                SingleTableReader reader = new SingleTableReader(dataForCurrYear);
                List<VisitData> personVisits = reader.selectWhereColEquals(UID, currPersonID);
                for (VisitData visit : personVisits) {
                    String visitTemplate = visit.getColumnData("Template");
                    String targetTemplate = associations.get(serviceReferred);
                    if (visitTemplate.equals(targetTemplate)) {
                        servicesReceived.put(serviceReferred, true);
                    }
                }
            }

        }
        return servicesReceived;
    }


}

