package resources;

import org.joda.time.DateTime;
import play.data.validation.Constraints;

public class PageResource {

    @Constraints.Required
    private String url;

    @Constraints.Required
    private Long accessTimestamp;

    public PageResource(){}

    public PageResource(String url) {
        this.url = url;
        this.accessTimestamp = new DateTime().getMillis();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Long getAccessTimestamp() {
        return accessTimestamp;
    }

    public void setAccessTimestamp(Long accessTimestamp) {
        this.accessTimestamp = accessTimestamp;
    }
}

