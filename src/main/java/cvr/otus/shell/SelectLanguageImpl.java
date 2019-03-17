package cvr.otus.shell;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class SelectLanguageImpl implements SelectLanguage {


    @Override
    public String selectLanguage() {
        byte[] bytes = new byte[2];
        try {
            int read = System.in.read(bytes,0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, Charset.defaultCharset());
    }


}
