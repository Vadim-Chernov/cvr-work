package cvr.otus.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Student {

    private int id;
    private String name;
    private String password;
    private String role;
}
