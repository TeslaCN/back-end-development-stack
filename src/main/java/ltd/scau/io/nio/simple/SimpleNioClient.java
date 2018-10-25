package ltd.scau.io.nio.simple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Weijie Wu
 */
public class SimpleNioClient {

    private static final Log logger = LogFactory.getLog(SimpleNioClient.class);

    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 12345));
        buffer.put("hello, nio".getBytes());
        buffer.flip();
        channel.write(buffer);
    }
}
