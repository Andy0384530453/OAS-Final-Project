
package com.example.Ex.DTO;

import com.example.Ex.Entity.Member;
import java.util.List;

public class CollectivityDTO {
    private String id;
    private String number;
    private String name;
    private String location;
    private boolean federationApproval;
    private CollectivityStructureDTO structure;
    private List<Member> members;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isFederationApproval() { return federationApproval; }
    public void setFederationApproval(boolean federationApproval) { this.federationApproval = federationApproval; }
    public CollectivityStructureDTO getStructure() { return structure; }
    public void setStructure(CollectivityStructureDTO structure) { this.structure = structure; }
    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }
}