package cvr.otus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PrintServiceImpl implements PrintService {

    @Autowired
    private MessageSource messageSource;


    @Override
    public void println(String message) {
        String msg = messageSource.getMessage("star", new String[]{message}, Locale.ENGLISH);
        System.out.println(msg);
    }

    @Override
    public void print(String message) {
        String msg = messageSource.getMessage("star", new String[]{message}, Locale.ENGLISH);
        System.out.print(msg);
    }
}
