package com.example.Ex.Repository;

import java.sql.PreparedStatement;
import java.util.List;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.MembershipFee;


public class CollectivityMemberShipFee {

    private final DBConnection connection;

    public CollectivityMemberShipFee(DBConnection connection) {
        this.connection = connection;
    }


    public List<MembershipFee> getAllMemberShipFeeForACollectivity(int idCollectivity){
        try {
            String sql = """
            SELECT eligibleFrom, frequency, amount, label, id, status FROM MemberShipFee 
                    """;
            PreparedStatement ps = co
            
            
        } catch (Exception e) {
           
        }

    }
}