package com.example.Ex.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.Ex.Entity.CollectivityLocalStatistics;
import com.example.Ex.Repository.StatisticsRepository;

public class StatisticServices {
    private final StatisticsRepository statisticsRepository;
    

    public StatisticServices(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<CollectivityLocalStatistics> getCollectivityStatisticValid(String collectivityId, LocalDate from, LocalDate to) throws SQLException{
        if (from == null || to == null) {
            throw new RuntimeException("Dates are required in collectivity statistics");
            
        }else if(from.isAfter(to)){
            throw new RuntimeException("Start date must be before end date");
        }

        List<CollectivityLocalStatistics> allCollectivityStatistic = statisticsRepository.getCollectivityLocalStatistics(collectivityId, from, to);

        return allCollectivityStatistic;
    }

}

    

