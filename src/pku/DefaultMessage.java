package pku;

import java.io.Serializable;

/**
 * 字节消息的实现
 * 字节消息的消息头为KeyValue结构，消息主体为byte数组
 */

public class DefaultMessage implements ByteMessage,Serializable {
    private static final long serialVersionUID = 1L;

    private KeyValue headers = new DefaultKeyValue();
    private byte[] body;

    public DefaultMessage(){
    }

    public DefaultMessage(byte[] body){
        this.body = body;
    }

    @Override
    public KeyValue headers() {
        return headers;
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    @Override
    public void setHeaders(KeyValue headers) {
        this.headers = headers;
    }

    @Override
    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public ByteMessage putHeaders(String key, int value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public ByteMessage putHeaders(String key, long value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public ByteMessage putHeaders(String key, double value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public ByteMessage putHeaders(String key, String value) {
        headers.put(key, value);
        return this;
    }
}
