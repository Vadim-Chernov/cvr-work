package cvr.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:file-path.properties")
public class StudentDaoImpl implements StudentDao {

    private String csvFile;


    StudentDaoImpl(@Value("${students}")String csvFile) {
        this.csvFile = csvFile;
    }

    private List<Student> loadStudents() throws IOException {
        List<Student> reslt = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null)
            reslt.add(new Student(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2]));

        return reslt;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> students = null;
        try {
            students = loadStudents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
