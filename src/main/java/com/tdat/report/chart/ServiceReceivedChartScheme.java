package com.tdat.report.chart;

import com.tdat.data.analysis.ServiceReceivedVerifier;
import com.tdat.report.JsonConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A chart to display the amount of users who receive the services they were
 * referred to
 */
public class ServiceReceivedChartScheme extends ChartScheme {

    public ServiceReceivedChartScheme(ChartType graphType) {
        super(graphType);
    }

    @Override
    public String toJson() {
        this.clear();
        
        List<String> servicesList = ServiceReceivedVerifier.getAllServices();
        Collections.sort(servicesList);
        ChartDataSet servicesReferred = new ChartDataSet("Referred", new ArrayList<>());
        ChartDataSet servicesReceived = new ChartDataSet("Received", new ArrayList<>());
        Map<String, Integer> servicesReferredMap = ServiceReceivedVerifier.getReferralsCount();
        Map<String, Integer> servicesReceivedMap = ServiceReceivedVerifier.checkAllServicesReceived();
        for (String service : servicesList) {
            servicesReferred.addData(servicesReferredMap.get(service));
            servicesReceived.addData(servicesReceivedMap.get(service));
        }

        List<String> trimmedServicesList = new ArrayList<>();
        for (String service : servicesList) {
            trimmedServicesList.add(service.replace(" Referrals", ""));
        }

        this.getDataSet().addAll(Arrays.asList(servicesReferred, servicesReceived));
        this.getXAxisLabels().addAll(trimmedServicesList);

        return JsonConverter.serializeObject(this);
    }
}
