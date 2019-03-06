package cvr.otus.utils;

import java.util.Iterator;
import java.util.Scanner;

public interface PrintService {
    void say(String message);
    void say(String key,String additional);
    void say(String key,String[] additional);
    void sayln(String message);
    void sayln(String key,String[] additional);
    void sayln(String key,String additional);
}
