package com.example.Ex.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Ex.Entity.CollectivityLocalStatistics;
import com.example.Ex.Repository.StatisticsRepository;

@Service
public class StatisticServices {
    private final StatisticsRepository statisticsRepository;
    

    public StatisticServices(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<CollectivityLocalStatistics> getCollectivityStatisticValid(String collectivityId, String from, String to) throws SQLException{
        if (from == null || to == null) {
            throw new RuntimeException("Dates are required in collectivity statistics");
            
        }

        List<CollectivityLocalStatistics> allCollectivityStatistic = statisticsRepository.getCollectivityLocalStatistics(collectivityId, from, to);

        return allCollectivityStatistic;
    }

}

    

