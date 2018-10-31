package ltd.scau.io.aio.simple;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Weijie Wu
 */
public class SimpleAioServer implements Runnable {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread t = new Thread(new SimpleAioServer());
        t.start();
    }

    @Override
    public void run() {
        AsynchronousChannelGroup channelGroup = null;
        AsynchronousServerSocketChannel assc = null;
        try {
            channelGroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
            assc = AsynchronousServerSocketChannel.open(channelGroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assc.bind(new InetSocketAddress(54321));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AsynchronousServerSocketChannel finalAssc = assc;
        assc.accept(this, new CompletionHandler<AsynchronousSocketChannel, SimpleAioServer>() {

            private final ByteBuffer byteBuffer = ByteBuffer.allocate(8);

            @Override
            public void completed(AsynchronousSocketChannel channel, SimpleAioServer attachment) {
                finalAssc.accept(null, this);
                System.out.println(channel + "Complete");
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                channel.read(byteBuffer, os, new CompletionHandler<Integer, ByteArrayOutputStream>() {
                    @Override
                    public void completed(Integer result, ByteArrayOutputStream attachment) {
                        if (result == -1) {
                            try {
                                channel.close();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        byteBuffer.flip();
                        byte[] b = new byte[result];
                        byteBuffer.get(b);
                        try {
                            attachment.write(b);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String s = new String(b);
                        System.out.println(s);
                        byteBuffer.clear();
                        channel.read(byteBuffer, attachment, this);
                    }

                    @Override
                    public void failed(Throwable exc, ByteArrayOutputStream attachment) {
                        exc.printStackTrace();
                    }
                });

                /**
                 * 异步会直接跑完以下代码，所以下面代码可能不会输出结果
                 */
                String s = new String(os.toByteArray(), Charset.forName("UTF-8"));
                System.out.println(s);
                Future<Integer> write = channel.write(ByteBuffer.wrap(("HTTP/1.1 200 OK\r\n" +
                        "content-type: text/plain\n" +
                        "content-length: 0\r\n").getBytes()));
                try {
                    System.out.println(write.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, SimpleAioServer attachment) {
                exc.printStackTrace();
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
