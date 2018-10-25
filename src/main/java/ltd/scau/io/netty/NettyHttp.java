package ltd.scau.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author Weijie Wu
 */
public class NettyHttp {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .localAddress(2333)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast("codec", new HttpServerCodec())
                                .addLast("s", new SimpleChannelInboundHandler<HttpRequest>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                                        System.out.println(msg);
                                        HttpResponse httpResponse = new DefaultFullHttpResponse(
                                                HttpVersion.HTTP_1_1,
                                                HttpResponseStatus.FOUND,
                                                Unpooled.wrappedBuffer("hello, world".getBytes(CharsetUtil.UTF_8)));
                                        ctx.writeAndFlush(httpResponse);
                                    }
                                });
                    }
                });
        sb.bind().sync();
    }
}
