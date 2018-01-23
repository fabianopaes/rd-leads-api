package utils;

import resources.ContactResource;
import resources.PageResource;

import java.util.Arrays;
import java.util.List;

public class ResourceFactory {

    public static ContactResource createContactResource(String email, String url){
        return createContactResource(email, Arrays.asList(new PageResource(url)));
    }

    public static ContactResource createContactResource(String email,List<PageResource> pages){
        return new ContactResource(email, pages);
    }

}
