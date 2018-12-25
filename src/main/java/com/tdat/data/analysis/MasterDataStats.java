package com.tdat.data.analysis;

import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to hold various methods to acquire stats/counts about MasterData. These methods will
 * mostly be used to address questions posted by TEQ.
 */
public class MasterDataStats {

    /**
     * A method that returns a sorted list of all years in MasterData
     */
    public static List<Year> getAllYears() {
        List<Year> allYears = new ArrayList<>();
        allYears.addAll(MasterData.getServiceProvidedData().keySet());
        Collections.sort(allYears);
        return allYears;
    }

    public static List<String> getAllYearsAsString() {
        List<String> allYearsString = new ArrayList<>();
        for (Year curr : getAllYears()) {
            allYearsString.add(curr.toString());
        }
        return allYearsString;
    }

    public static Integer getPeopleCount() {
        return MasterData.initialVisitData.getVisitsData().size();
    }

    public static Integer getVisitsCountByYear(Year year) {
        TableData yearData = MasterData.getYearData(year);
        return yearData.getVisitsData().size();
    }

    public static Integer getTotalVisits() {
        int count = 0;
        for (Year year : getAllYears()) {
            count += getVisitsCountByYear(year);
        }
        return count;
    }
}
