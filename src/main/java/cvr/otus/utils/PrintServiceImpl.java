package cvr.otus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PrintServiceImpl implements PrintService {

    @Autowired
    private MessageSource messageSource;


    @Override
    public void println(String message) {
        String msg = messageSource.getMessage("star", new String[]{message}, Locale.ENGLISH);
        Say.messageln(msg);
    }

    @Override
    public void print(String message) {
        String msg = messageSource.getMessage("star", new String[]{message}, Locale.ENGLISH);
        Say.message(msg);
    }
}

@Component
class PrintServiceBean{
    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasename("/i18n/bundle");
        return ms;
    }

}