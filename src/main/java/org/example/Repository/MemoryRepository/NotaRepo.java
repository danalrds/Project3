package org.example.Repository.MemoryRepository;
import org.example.Validator.IValidator;
import org.example.Domain.Nota;

public class NotaRepo extends AbstractCrudRepo<Integer,Nota > {
    public NotaRepo(IValidator<Nota> v){ super(v);
    }
}