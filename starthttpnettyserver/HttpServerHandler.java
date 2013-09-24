package starthttpnettyserver;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import java.net.InetSocketAddress;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private String IPAdres;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        IPAdres = address.getHostString();

        if (msg instanceof HttpRequest) {
            ServerControler.incrementConnection();
            HttpRequest request = (HttpRequest) msg;
            if (is100ContinueExpected(request)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }

            boolean keepAlive = isKeepAlive(request);
            FullHttpResponse response = WebStatistic.RequestHandle(request, IPAdres);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, Values.KEEP_ALIVE);
                ctx.write(response);
            }
        } else if (msg instanceof LastHttpContent) {
            ServerControler.decrementConnection();
        }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ServerControler.decrementConnection();
        ctx.close();
    }
}