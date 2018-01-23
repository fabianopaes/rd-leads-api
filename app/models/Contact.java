package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseModel{

    @Column(name = "email")
    @Constraints.Required
    @Constraints.Email
    @NotNull
    public String email;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    @JsonManagedReference
    public List<Page> pages;

}
