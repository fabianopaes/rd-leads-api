package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "PAGE")
public class Page extends BaseModel{

    @Column(name = "URL")
    public String url;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", nullable = false)
    public Contact contact;

}
