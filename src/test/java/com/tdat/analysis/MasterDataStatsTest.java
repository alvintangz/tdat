package com.tdat.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tdat.data.MasterData;
import com.tdat.data.MockData;
import com.tdat.data.analysis.MasterDataStats;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MasterDataStatsTest {
    @BeforeAll
    public static void setUp(){
        MockData.populateInitialVisitData();
        MockData.populateServiceProvidedData();
    }

    @Test
    public void testGetAllYears() {
        Set<Year> expected = new HashSet<>(Arrays.asList(Year.of(2018), Year.of(2017)));
        Set<Year> actual = new HashSet<>(MasterDataStats.getAllYears());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllYearsAsString() {
        Set<String> expected = new HashSet<>(Arrays.asList("2017", "2018"));
        Set<String> actual = new HashSet<>(MasterDataStats.getAllYearsAsString());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPeopleCount() {
        int expected = 2;
        int actual = MasterDataStats.getPeopleCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetVisitsCountByYear() {
        int expected = 2;
        int actual = MasterDataStats.getVisitsCountByYear(Year.of(2018));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTotalVisits() {
        int expected = 4;
        int actual = MasterDataStats.getTotalVisits();
        assertEquals(expected, actual);
    }

    @AfterAll
    static void cleanUp(){
        MasterData.clear();
    }
}