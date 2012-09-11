package org.ws4d.coap.client;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.ws4d.coap.connection.BasicCoapChannelManager;
import org.ws4d.coap.interfaces.CoapClient;
import org.ws4d.coap.interfaces.CoapClientChannel;
import org.ws4d.coap.interfaces.CoapChannelManager;
import org.ws4d.coap.interfaces.CoapRequest;
import org.ws4d.coap.interfaces.CoapResponse;
import org.ws4d.coap.messages.CoapMediaType;
import org.ws4d.coap.messages.CoapRequestCode;

public class Client {

    private static final String SERVER_ADDRESS = "fec8:0:0:0:0:0:0:8";
    private static final int PORT = 61616;

    private final CoapChannelManager manager;

    public Client() {
        super();

        this.manager = BasicCoapChannelManager.getInstance();
    }

    public Future<BigDecimal> getTemperature() throws UnknownHostException {
        final Future<BigDecimal> result = new Future<BigDecimal>(); 
        final CoapClientChannel channel = this.manager.connect(new CoapClient() {

            @Override
            public void onResponse(final CoapClientChannel channel, final CoapResponse response) {
                final BigDecimal temperature = new BigDecimal(char_to_uint16(response.getPayload())).divide(new BigDecimal(100)).subtract(new BigDecimal("273.15"));
                result.set(temperature);
                channel.close();
            }

            @Override
            public void onConnectionFailed(final CoapClientChannel channel, final boolean notReachable, final boolean resetByServer) {
                result.set(null);
            }
        }, InetAddress.getByName(SERVER_ADDRESS), PORT);
        final CoapRequest request = channel.createRequest(true, CoapRequestCode.GET);
        request.setContentType(CoapMediaType.octet_stream);
        request.setUriPath("/st");
        channel.sendMessage(request);
        
        return result;
    }

    public Future<BigDecimal> getHumidity() throws UnknownHostException {
        final Future<BigDecimal> result = new Future<BigDecimal>(); 
        final CoapClientChannel channel = this.manager.connect(new CoapClient() {

            @Override
            public void onResponse(final CoapClientChannel channel, final CoapResponse response) {
                final BigDecimal humidity = new BigDecimal(char_to_uint16(response.getPayload())).divide(new BigDecimal(100));
                result.set(humidity);
                channel.close();
            }

            @Override
            public void onConnectionFailed(final CoapClientChannel channel, final boolean notReachable, final boolean resetByServer) {
                result.set(null);
            }
        }, InetAddress.getByName(SERVER_ADDRESS), PORT);
        final CoapRequest request = channel.createRequest(true, CoapRequestCode.GET);
        request.setContentType(CoapMediaType.octet_stream);
        request.setUriPath("/sh");
        channel.sendMessage(request);
        
        return result;
    }
    
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(new Client().getTemperature().get());
        System.out.println(new Client().getHumidity().get());
    }
    
    private static int char_to_uint16(byte[] by) {
        int value = 0;
        for (int i = 0; i < by.length; i++)
        {
           value += ((long) by[i] & 0xffL) << (8 * i);
        }
        return value;
    }
}

