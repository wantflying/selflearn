import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hello_Trans {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //读取文件流
        File file = new File("week1-jvm/Hello.xlass");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = bufferedInputStream.read(temp)) != -1){
            byteArrayOutputStream.write(temp,0,size);
        }
        bufferedInputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < byteArray.length; i++) {
            byte b = byteArray[i];
            b = (byte) (255 - b);
            byteArray[i] = b;
        }
        File file1 = new File("week1-jvm/Hello.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(byteArray);

        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> hello = helloClassLoader.findClass("Hello");
        Method hello_method = hello.getDeclaredMethod("hello");
        Object o = hello.newInstance();
        hello_method.invoke(o);
        System.out.println(hello.getClassLoader().getClass().getName());
    }
}