package cvr.otus.domain;

import lombok.Data;

@Data

public class Student {
    
    private int id;
    private String name;
    private String password;

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
