// TODO updating projectProposals updates proposer in Project and vice versa 

package assignment2;

import java.util.ArrayList;
import java.util.Objects;

public class Project {
    final private StaffMember proposer;
    final private Stream stream;

    public Project(StaffMember proposer, Stream stream) {
        this.proposer = proposer;
        this.stream = stream;
    }

    public StaffMember getProposer() {
        return this.proposer;
    }


    public Stream getStream() {
        return this.stream;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(proposer, project.proposer) && Objects.equals(stream, project.stream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposer, stream);
    }

    @Override
    public String toString() {
        return "{" +
            " proposer='" + getProposer() + "'" +
            ", stream='" + getStream() + "'" +
            "}";
    }


}
