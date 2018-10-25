package ltd.scau.io.nio.simple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Weijie Wu
 */
public class SimpleNioServer {

    private static final Log logger = LogFactory.getLog(SimpleNioServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(12345));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                SelectableChannel channel = key.channel();

                if (key.isAcceptable()) {
                    logger.info("Acceptable >>> " + channel);
                    ServerSocketChannel ch = (ServerSocketChannel) channel;
                    SocketChannel accept = ch.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }

                if (key.isConnectable()) {
                    logger.info("Connectable >>> " + channel);
                }

                if (key.isReadable()) {
                    logger.info("READABLE");
                    SocketChannel ch = (SocketChannel) channel;
                    if (handleRead(ch)) {
                        ch.write(ByteBuffer.wrap("200".getBytes()));
                    }
                }

//                if (key.isWritable()) {
//                    logger.info("Writable >>> " + channel);
//                }

                if (!key.isValid()) {
                    logger.info("Not Valid >>> " + channel);
                }
            }
        }
    }

    private static boolean handleRead(SocketChannel ch) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        int size;
        boolean read = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((size = ch.read(buffer)) > 0) {
            read = true;
            buffer.flip();
            byte[] b = new byte[size];
            buffer.get(b);
            baos.write(b);
            buffer.clear();
        }
        if (read) {
            String s = new String(baos.toByteArray());
            logger.info("READ >>> " + s);
        }
        if (size == -1) {
            logger.info("Detected close");
            ch.close();
            return false;
        }
        return true;
    }
}
