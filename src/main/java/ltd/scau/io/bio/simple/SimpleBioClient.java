package ltd.scau.io.bio.simple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Weijie Wu
 */
public class SimpleBioClient {

    private static final Log logger = LogFactory.getLog(SimpleBioClient.class);

    public static void main(String[] args) throws IOException {
        final String host = "127.0.0.1";
        final int port = 12345;
        try (Socket socket = new Socket(host, port);) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello, world".getBytes());
        }
    }
}
