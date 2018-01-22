package repository;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import play.db.ebean.Model;

import java.util.List;

public class BaseRepository<T extends Model> {

    protected Model.Finder<Long, T> finder;

    public BaseRepository(Class<T> classType){
       this.finder = new Model.Finder<Long, T> (Long.class, classType );
    }

    protected EbeanServer getEbeanServer(){
        return Ebean.getServer("default");
    }

    public List<T> findAll(){
        return finder.all();
    }


}
