package starthttpnettyserver;

import java.util.Date;
import starthttpnettyserver.entity.GlobalStatistic;
import java.util.List;
import io.netty.util.CharsetUtil;
import io.netty.buffer.Unpooled;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import starthttpnettyserver.db.DBManager;
import starthttpnettyserver.entity.IPRequest;
import starthttpnettyserver.entity.UrlRedirect;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

public class WebStatistic {

    public static final String STATUS = "/status";
    public static final String HELLO = "/hello";
    public static final String REDIRECT = "/redirect";
    public static int SLEEP_TIME = 10;

    public static FullHttpResponse RequestHandle(HttpRequest httpRequest, String ip) {

        String uri = httpRequest.getUri().toLowerCase();
        FullHttpResponse response = null;

        if (uri.equals(STATUS)) {
            DBManager.addIpCount(ip);

            StringBuilder builderHttpResponse = new StringBuilder();
            builderHttpResponse.append(" <html> <head>  </head> <body>");
            builderHttpResponse.append(" <h2> <p>Number of all requset: " + DBManager.getNumberOfAllRequest() + " </p> </h2>");
            builderHttpResponse.append(" <table border=\"1\" width=\"100%\" cellpadding=\"5\">");
            builderHttpResponse.append(" <tr> <th>IP</th>  <th>Count of request</th>  <th>Time lsat request</th> </tr>");
            List<IPRequest> allRequest = DBManager.getAllIPRequest();
            for (int i = 0; i < allRequest.size(); i++) {
                IPRequest request = allRequest.get(i);
                builderHttpResponse.append(" <tr> <td>" + request.getIp() + "</td>  <td>" + request.getCount() + "</td>  <td>" + new Date() + "</td> </tr>");
            }
            builderHttpResponse.append(" </table>");
            builderHttpResponse.append("<p>-------------------------------------------</p>");
            builderHttpResponse.append(" <table border=\"1\" width=\"100%\" cellpadding=\"5\">");
            builderHttpResponse.append(" <tr> <th>URL</th>  <th>Count of redirect</th>  </tr>");
            List<UrlRedirect> allRedorect = DBManager.getAllUrlRedirect();
            for (int i = 0; i < allRedorect.size(); i++) {
                UrlRedirect request = allRedorect.get(i);
                builderHttpResponse.append(" <tr> <td>" + request.getUrl() + "</td>  <td>" + request.getCount() + "</td>  </tr>");
            }
            builderHttpResponse.append(" </table>");
            builderHttpResponse.append(" <h2> <p>Number of active connections: " + ServerControler.getNumberAllActiveConnection() + " </p> </h2>");
            builderHttpResponse.append("<p>-------------------------------------------</p>");
            builderHttpResponse.append(" <table border=\"1\" width=\"100%\" cellpadding=\"5\">");
            builderHttpResponse.append(" <tr> <th>SRC_IP</th>  <th>URI</th> <th>Time stemp</th> <th> Sent bytes</th> <th>Received bytes</th> <th>Speed_(byte/sec)</th> </tr>");

            List<GlobalStatistic> allStatisticRequest = DBManager.getAllStatistic();

            int size = allStatisticRequest.size();
            int second = 0;

            if (size > 15) {
                int first = size;
                second = first - 16;
            }
            for (int i = second; i < allStatisticRequest.size(); i++) {
                GlobalStatistic statistic = allStatisticRequest.get(i);
                builderHttpResponse.append(" <tr> <td>" + statistic.getSrc_ip() + "</td>  <td>" + statistic.getUri() + "</td> <td>"
                        + statistic.getTime_stamp() + "</td> <td>" + statistic.getSent_bytes() + "</td>  <td>"
                        + statistic.getReceived_bytes() + "</td> <td>" + statistic.getSppedTransmission() + "</td></tr>");
            }
            builderHttpResponse.append(" </table>");
            builderHttpResponse.append(" <h2> <p> Number of unique queries: " + DBManager.searchUniqueRequest() + " </p> </h2>");
            builderHttpResponse.append(" </body> </html>");

            ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(builderHttpResponse.toString(), CharsetUtil.US_ASCII));
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, buf);
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            
            DBManager.addAllStatisticRequest(ip, uri, response.toString().getBytes().length+ builderHttpResponse.toString().getBytes().length, httpRequest.toString().getBytes().length, 1);
            

             
             
             
             
             
        } else if (uri.equals(HELLO)) {
            DBManager.addIpCount(ip);
            try {
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hello World", CharsetUtil.US_ASCII));
            String hello = "Hello World";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, buf);
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            DBManager.addAllStatisticRequest(ip, uri, response.toString().getBytes().length + hello.getBytes().length, httpRequest.toString().getBytes().length, 1);

        } else if (uri.contains(REDIRECT) && uri.contains("url")) {
           String redirect = "redirect" + uri;
            DBManager.addIpCount(ip);
            String redirectToSite = parseUri(uri);
            // ADD TO DB
            DBManager.addURLRedirect(redirectToSite);
            ByteBuf buf23 = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("", CharsetUtil.US_ASCII));
            response = new DefaultFullHttpResponse(HTTP_1_1, TEMPORARY_REDIRECT, buf23);
            response.headers().set(LOCATION, redirectToSite);
            DBManager.addAllStatisticRequest(ip, uri, response.toString().getBytes().length + redirect.getBytes().length, httpRequest.toString().getBytes().length, 1);
       
        } else {
            ByteBuf buf2 = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Bad request", CharsetUtil.US_ASCII));
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, buf2);
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        }
        return response;
    }

    public static String parseUri(String uri) {
        int firstIndex = 0;
        int secondIndex = 0;

        String zeroProbel = uri.trim();
        for (int i = 0; i < zeroProbel.length(); i++) {
            if (zeroProbel.charAt(i) == '=') {
                firstIndex = i + 1;
            }
            secondIndex = i + 1;
        }
        String newNameWebsite = zeroProbel.substring(firstIndex, secondIndex);

        if (newNameWebsite.substring(0, 7).equals("http://")) {
            newNameWebsite = newNameWebsite;
        } else {
            newNameWebsite = "http://" + newNameWebsite;
        }
        return newNameWebsite;
    }
}
