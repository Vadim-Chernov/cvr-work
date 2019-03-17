package cvr.otus.shell;

import org.springframework.stereotype.Service;

@Service
public class SelectLanguageImpl implements SelectLanguage {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String bye(String name) {
        return "Bye " + name;
    }

}
