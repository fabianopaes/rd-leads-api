package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseModel{

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "email")
    @Constraints.Required
    @Constraints.Email
    @NotNull
    public String email;



    @Override
    public Long getId() {
        return id;
    }

}
