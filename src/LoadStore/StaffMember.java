package LoadStore;

import java.util.concurrent.atomic.AtomicInteger;

public class StaffMember {

    private String fullName;
    private String researchActivity;
    private SpecialFocus specialFocus;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
private int id;
    public StaffMember(String fullName, String researchActivity, SpecialFocus specialFocus) {
        this.fullName = fullName;
        this.researchActivity = researchActivity;
        this.specialFocus = specialFocus;
        this.id=ID_GENERATOR.getAndIncrement();
    }
    public StaffMember(int id,String fullName, String researchActivity, String specialFocus) {
        this.fullName = fullName;
        this.researchActivity = researchActivity;
        this.specialFocus = SpecialFocus.valueOf(specialFocus);
        this.id=id;
        
    }

    public String getFullName() {
        return fullName;
    }

    public String getResearchActivity() {
        return researchActivity;
    }

    public SpecialFocus getSpecialFocus() {
        return specialFocus;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setResearchActivity(String researchActivity) {
		this.researchActivity = researchActivity;
	}

	public void setSpecialFocus(SpecialFocus specialFocus) {
		this.specialFocus = specialFocus;
	}
    public String toCsvString() {
    	return id+"," +fullName+","+researchActivity+","+specialFocus.toString()+"\n";
    }
}
