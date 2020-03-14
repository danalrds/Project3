package org.example.Service.XMLFileService;
import org.example.Domain.*;
import org.example.Exceptions.ValidatorException;
import org.example.Repository.XMLFileRepository.AbstractXMLRepo;

public abstract class AbstractXMLService<ID,E extends HasId<ID>> {
    private AbstractXMLRepo xmlrepo;

    public AbstractXMLService(AbstractXMLRepo xmlrepo)  {
        this.xmlrepo = xmlrepo;
    }

    protected abstract E extractEntity(String[] params);
        //return new Student(params[0],params[1],Integer.parseInt(params[2]),params[3],params[4]);
    //}

    public void add(String params[]) throws ValidatorException {
        E e=extractEntity(params);
        xmlrepo.save(e);
    }
    public void remove(ID id){
        xmlrepo.delete(id);
    }
    public void update(String params[]){
        E s=extractEntity(params);
        xmlrepo.update(s);
    }
    public E findOne(ID id){
        return (E) xmlrepo.findOne(id);
    }
    public Iterable<E>findAll(){
        return xmlrepo.findAll();
    }
    public int getSize(){
        return xmlrepo.getSize();
    }
}