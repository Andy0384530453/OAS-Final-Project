package com.example.Ex.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.CollectivityLocalStatistics;
import com.example.Ex.Entity.Member;

@Repository
public class StatisticsRepository {
    private final DBConnection dbConnection;
    private final MemberRepository memberRepository;

    public StatisticsRepository(DBConnection dbConnection, MemberRepository memberRepository) {
        this.dbConnection = dbConnection;
        this.memberRepository = memberRepository;
    }

    private Connection getConnection() throws SQLException{
        return dbConnection.getDBConnection();
    }

    public long getMemberEarnedAmount(String memberId, String startDate, String endDate) throws SQLException{
        String getMemberEarnedAmountSQL = """
         SELECT SUM(mp.amount) as totalEarnedAmount FROM member_payments mp 
            WHERE mp.member_id = ?
            AND creation_date 
            BETWEEN ? AND ? 
        """;
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(getMemberEarnedAmountSQL);
            ps.setString(1, memberId);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long memberEarnedAmount = rs.getLong("totalEarnedAmount");
                return memberEarnedAmount;
                
                
            }
        }
    
        return 0l;
        
    } 

    public long getMemberUnpaidAmount(String collectivityId, String eligible_from, String memberId, String from, String to) throws SQLException{
        String getMemberUnpaidAmountSQL = """
            SELECT SUM(mf.amount) AS totalUnpaidAmount from membership_fees mf 
            WHERE mf.collectivity_id = ? 
            AND mf.status = 'ACTIVE' 
            AND mf.eligible_from <= ?
            AND mf.id NOT IN (        
                SELECT mp.membership_fee_id
                FROM member_payments mp
                WHERE mp.member_id = ?
                AND mp.creation_date BETWEEN ? AND ?
        )
                """;

        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(getMemberUnpaidAmountSQL);
            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(eligible_from));
            ps.setString(3, memberId);
            ps.setDate(4, Date.valueOf(from));
            ps.setDate(5, Date.valueOf(to));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long memberUnpaidAmount = rs.getLong("totalUnpaidAmount");
                System.out.println(memberUnpaidAmount);
                return memberUnpaidAmount;
                
            }

        }
        return 0l;
    }

    public List<CollectivityLocalStatistics> getCollectivityLocalStatistics(String collectivityId,String from, String to ) throws SQLException{
        
        List<CollectivityLocalStatistics> collectivityLocalStatisticsList = new ArrayList<>();
        List<Member> members = memberRepository.findByCollectivityId(collectivityId);
    
        for(Member member: members){
        CollectivityLocalStatistics collectivityLocalStatistics
         = new CollectivityLocalStatistics();
            collectivityLocalStatistics.setMember(member);
            collectivityLocalStatistics.setEarnedAmount(getMemberEarnedAmount(member.getId(), from, to));
            
            collectivityLocalStatistics.setUnpaidAmount(getMemberUnpaidAmount(collectivityId, from ,member.getId(), from ,to));

            collectivityLocalStatisticsList.add(collectivityLocalStatistics);
            

        }
        return collectivityLocalStatisticsList;
    }
    
}
