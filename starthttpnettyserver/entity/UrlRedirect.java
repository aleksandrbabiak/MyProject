package starthttpnettyserver.entity;


public class UrlRedirect {
    private String url;
    private int count;

    public UrlRedirect(String url, int count) {
        this.url = url;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
    
    
}
