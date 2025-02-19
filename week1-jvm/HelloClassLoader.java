import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloClassLoader extends ClassLoader{

    @Override
    public Class<?> findClass(String name) {
        File file = new File("week1-jvm/Hello.class");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            return defineClass(name,bytes,0,bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
