package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseModel{

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Override
    public Long getId() {
        return id;
    }

}
