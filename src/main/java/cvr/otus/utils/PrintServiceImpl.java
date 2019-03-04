package cvr.otus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("classpath:file-path.properties")
public class PrintServiceImpl implements PrintService {

    @Value("${language}")
    private String language;

    @Autowired
    private MessageSource messageSource;

    private Locale locale;

    public PrintServiceImpl() {
        init();
    }

    @Override
    public void say(String message) {
        init();
        System.out.print(messageSource.getMessage(message, null, locale));
    }

    @Override
    public void sayln(String message) {
        init();
        System.out.println(messageSource.getMessage(message, null, locale));
    }

    @Override
    public void say(String key, String additional) {
        init();
        System.out.println(messageSource.getMessage(key, new String[]{additional}, locale));
    }

    @Override
    public void say(String key, String[] additional) {
        init();
        System.out.println(messageSource.getMessage(key, additional, locale));
    }

    @Override
    public void sayln(String message, String[] additional) {
        init();
        System.out.println(messageSource.getMessage(message, additional, locale));
    }

    @Override
    public void sayln(String key, String additional) {
        init();
        String[] args = {additional};
        String message1 = messageSource.getMessage(key, args, locale);
        System.out.println(message1);
    }

    private void init() {
        if (language != null)
            if (locale == null) {
                String[] loc = language.split(",");
                locale = new Locale(loc[0],loc[1]);
            }
    }
}