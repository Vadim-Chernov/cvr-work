package cvr.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.domain.Student;
import cvr.otus.fake.FakeData;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private List<Student> loadStudents() throws IOException {
        List<Student> reslt = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("students.csv"), ',', '"', 1);
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

    private void createFake() {
        FakeData.createStudents();
    }
}
