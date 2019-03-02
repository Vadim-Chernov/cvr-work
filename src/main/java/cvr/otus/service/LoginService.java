package cvr.otus.service;

import cvr.otus.domain.Student;

import java.util.Iterator;

public interface LoginService {
    Student login();
    void setScanner(Iterator<String> scanner);
}
