import java.io.FileReader;
import java.io.BufferedReader;

public class ProjectListGenerator {

    public ProjectListGenerator() { }

    generateProjectTestData(int maxSize) {

        ProjectList projectList = new ProjectList(maxSize);
        BufferedReader csvReader = new BufferedReader(new FileReader(staffmembers.csv));
        int rowNum = 1;
        while ((row = csvReader.readLine()) != null && projectList.size() < maxSize) {
            String[] data = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String[] projects = data[1].split(",");
            for (int i = 0; i < projects.length; i++) {
                projectList.addProject(new Project(projects[i], data[0],))
            }
            rowNum++;
        }
        csvReader.close();
    }
}