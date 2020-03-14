package org.example.Repository.MemoryRepository;
import org.example.Validator.IValidator;
import org.example.Domain.Student;

public class StudentRepo extends AbstractCrudRepo<String, Student> {
    public StudentRepo(IValidator<Student> v){ super(v);
    }
}