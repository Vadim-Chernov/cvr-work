package cvr.otus.utils;

public interface PrintService {
    void say(String message);
    void say(String key,String additional);
    void say(String key,String[] additional);
    void sayln(String message);
    void sayln(String key,String[] additional);
    void sayln(String key,String additional);
}
