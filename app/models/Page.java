package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import deserializers.DateTimeDeserializer;
import org.joda.time.DateTime;
import serializers.DateTimeSerializer;

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

    @Column(name = "ACCESS_TIMESTAMP")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    public DateTime accessTimestamp;

}
