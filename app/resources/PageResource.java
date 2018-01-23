package resources;

import play.data.validation.Constraints;

public class PageResource {

    @Constraints.Required
    private String url;

    public PageResource(){}

    public PageResource(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
