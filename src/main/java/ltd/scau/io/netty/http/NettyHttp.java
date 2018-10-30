package ltd.scau.io.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Weijie Wu
 */
public class NettyHttp {

    private static final Log logger = LogFactory.getLog(NettyHttp.class);

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .localAddress(54321)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast("codec", new HttpServerCodec())
                                .addLast("get", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        if (!(msg instanceof HttpRequest)) {
                                            ctx.fireChannelRead(msg);
                                        } else {
                                            HttpRequest request = (HttpRequest) msg;
                                            logger.info(request);
                                            ByteBuf content = Unpooled.wrappedBuffer("hello, world".getBytes());
                                            FullHttpResponse response = new DefaultFullHttpResponse(
                                                    HttpVersion.HTTP_1_1,
                                                    HttpResponseStatus.OK,
                                                    content
                                            );
                                            response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
                                            response.headers().add(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

                                            logger.info(response);
                                            ctx.writeAndFlush(response);
                                        }
                                    }
                                })
                        ;
                    }
                });
        sb.bind().sync();
    }
}
