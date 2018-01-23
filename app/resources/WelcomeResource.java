package resources;

import play.Play;

public class WelcomeResource {

    private String api;
    private String description;
    private String author;
    private String version;
    private String documentation;

    public static WelcomeResource create(){
        WelcomeResource resource = new WelcomeResource();
        resource.setApi(Play.application().configuration().getString("application.info.api"));
        resource.setDescription(Play.application().configuration().getString("application.info.description"));
        resource.setAuthor(Play.application().configuration().getString("application.info.author"));
        resource.setVersion(Play.application().configuration().getString("application.info.version"));
        resource.setVersion(Play.application().configuration().getString("application.info.version"));
        resource.setDocumentation(Play.application().configuration().getString("application.info.documentation"));
        return  resource;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
