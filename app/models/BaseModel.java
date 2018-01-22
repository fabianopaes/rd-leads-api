package models;


import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseModel extends Model {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "Version")
    public int version;


    public abstract Long getId();


    @Override
    public int hashCode(){
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }


/*    public boolean equals(Object object)
    {
        return object instanceof Serializable && equals((BaseModel) object);
    }


    public boolean equals(BaseModel baseModel){
        if (this == baseModel){
            return true;
        }

        return baseModel == null ? false : (baseModel.getId() == null
                || baseModel.getClass() == null
                ? getId() == null && this.getClass() == null
                : baseModel.getId().equals(getId()) && this.getClass().equals(baseModel.getClass()));
    }


    public static <T extends BaseModel> Page<T> findAll(Class<T> clazz,
                                                        int pageSize, int page, String fieldToOrder) {
        Query<T> query = Ebean.createQuery(clazz).order().asc(fieldToOrder);
        return query.findPagingList(pageSize).getPage(page);
    }*/

}
