package ltd.scau.io.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Weijie Wu
 */
public class SimpleNettyHttpClient {

    private static final Log logger = LogFactory.getLog(SimpleNettyHttpClient.class);

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.channel(NioSocketChannel.class)
                .remoteAddress("127.0.0.1", 54321)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast("code", new HttpClientCodec())
                            .addLast("m", new SimpleChannelInboundHandler<HttpResponse>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, HttpResponse msg) throws Exception {
                                    logger.info(msg);
                                }
                            })
                        ;
                    }
                })
                .group(group);

        Channel channel = b.connect().channel();

        FullHttpRequest message = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/hello-world");
        channel.writeAndFlush(message);
        channel.closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}
