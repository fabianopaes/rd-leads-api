package resources;

import play.data.validation.Constraints;

import javax.validation.Valid;
import java.util.List;

public class ContactResource {

    @Constraints.Required
    @Constraints.Email
    private String email;

    @Valid
    private List<PageResource> pages;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PageResource> getPages() {
        return pages;
    }

    public void setPages(List<PageResource> pages) {
        this.pages = pages;
    }
}
