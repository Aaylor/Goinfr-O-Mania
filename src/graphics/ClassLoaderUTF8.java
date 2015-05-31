package graphics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ClassLoaderUTF8 extends ClassLoader {
    public ClassLoaderUTF8(ClassLoader parent) {
        super(parent);
    }

    public InputStream getResourceAsStream(String name) {
        InputStream utf8in = getParent().getResourceAsStream(name);
        if (utf8in != null) {
            try {
                byte[] utf8Bytes = new byte[utf8in.available()];
                utf8in.read(utf8Bytes, 0, utf8Bytes.length);
                byte[] iso8859Bytes = new String(utf8Bytes, "UTF-8").getBytes("ISO-8859-1");
                return new ByteArrayInputStream(iso8859Bytes);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
