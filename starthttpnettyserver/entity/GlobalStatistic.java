package starthttpnettyserver.entity;

public class GlobalStatistic {
    
   private String src_ip;
   private String uri;
   private String time_stamp;
   private int sent_bytes;
   private int received_bytes;
   private int sppedTransmission;
    
   public GlobalStatistic(String src_ip, String uri, String time_stamp, int sent_bytes, int received_bytes, int speedTransmission){
   this.src_ip = src_ip;
   this.uri = uri;
   this.time_stamp = time_stamp;
   this.sent_bytes = sent_bytes;
   this.received_bytes = received_bytes;
   this.sppedTransmission = speedTransmission;
   }

    public int getReceived_bytes() {
        return received_bytes;
    }

    public void setReceived_bytes(int received_bytes) {
        this.received_bytes = received_bytes;
    }

    public int getSent_bytes() {
        return sent_bytes;
    }

    public void setSent_bytes(int sent_bytes) {
        this.sent_bytes = sent_bytes;
    }

    public int getSppedTransmission() {
        return sppedTransmission;
    }

    public void setSppedTransmission(int sppedTransmission) {
        this.sppedTransmission = sppedTransmission;
    }

    public String getSrc_ip() {
        return src_ip;
    }

    public void setSrc_ip(String src_ip) {
        this.src_ip = src_ip;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    
}
