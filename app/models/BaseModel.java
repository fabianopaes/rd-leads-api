package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import serializers.DateTimeSerializer;

@MappedSuperclass
public abstract class BaseModel extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    public Long id;

    @JsonIgnore
    @Version
    @Column(name = "Version")
    public int version;

/*    @Column(name="CREATED")
    public DateTime created;

    @Column(name="UPDATED")
    public DateTime updated;*/

    public BaseModel(){
/*        created = new DateTime();
        updated = new DateTime();*/
    }


    public Long getId() {
        return id;
    }


    @Override
    public int hashCode(){
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

}
