package starthttpnettyserver.entity;

public class IPRequest {
    private String ip;
    private  int count;    
    private String time;

    public IPRequest(String ip, int count, String time) {
        this.ip = ip;
        this.count = count;
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    
    
    
}
