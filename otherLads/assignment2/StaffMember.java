//TODO updating projectProposals updates proposer in Project and vice versa 

package assignment2;

import java.util.ArrayList;
import java.util.Objects;

public class StaffMember {
    final private String name;
    final private ArrayList<String> researchInterests;
    final private ArrayList<Stream> streams;
    final private ArrayList<Project> projectProposals;

    public StaffMember(String name, ArrayList<String> researchInterests, ArrayList<Stream> streams, ArrayList<Project> projectProposals) {
        this.name = name;
        this.researchInterests = researchInterests;
        this.streams = streams;
        this.projectProposals = projectProposals;
    }

    public String getName() {
        return this.name;
    }


    public ArrayList<String> getResearchInterests() {
        return this.researchInterests;
    }


    public ArrayList<Stream> getStreams() {
        return this.streams;
    }


    public ArrayList<Project> getProjectProposals() {
        return this.projectProposals;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StaffMember)) {
            return false;
        }
        StaffMember staffMember = (StaffMember) o;
        return Objects.equals(name, staffMember.name) && Objects.equals(researchInterests, staffMember.researchInterests) && Objects.equals(streams, staffMember.streams) && Objects.equals(projectProposals, staffMember.projectProposals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, researchInterests, streams, projectProposals);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", researchInterests='" + getResearchInterests() + "'" +
            ", streams='" + getStreams() + "'" +
            ", projectProposals='" + getProjectProposals() + "'" +
            "}";
    }
    

}