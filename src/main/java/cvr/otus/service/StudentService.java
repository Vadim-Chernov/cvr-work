package cvr.otus.service;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.FakeData;
import cvr.otus.domain.Student;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService {
    private List<Student> students;

    @Override
    public List<Student> getStudentList() {
        if (students == null) {
            try {
                students = loadStudents();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return students;
    }


    @Override
    public Student login(String name, String password) {
        List<Student> studentList = getStudentList();


        Student student = studentList.stream()
                .filter((st) -> name.equalsIgnoreCase(st.getName()) && password.equals(st.getPassword()))
                .findAny().orElse(null);

        return student;
    }

    private static List<Student> loadStudents() throws IOException {
        List<Student> reslt = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("students.csv"), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                reslt.add(new Student(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2]));
            }
        }
        return reslt;
    }

}
