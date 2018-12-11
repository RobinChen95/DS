package pku;

/**
 *
 * 字节信息接口
 * 字节信息的基本结构为：消息头Header+消息主体Body
 *
 */

public interface ByteMessage {

    public KeyValue headers();

    byte[] getBody();

    void setHeaders(KeyValue header);

    void setBody(byte[] body);

    public ByteMessage putHeaders(String key, int value);

    public ByteMessage putHeaders(String key, long value);

    public ByteMessage putHeaders(String key, double value);

    public ByteMessage putHeaders(String key, String value) ;

}
