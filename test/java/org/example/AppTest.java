package org.example;

import org.example.Domain.Student;
import org.example.Exceptions.ValidatorException;
import org.example.Repository.TxtFileRepository.StudentFileRepo;
import org.example.Repository.XMLFileRepository.StudentXMLRepo;
import org.example.Service.TxtFileService.AbstractService;
import org.example.Service.TxtFileService.StudentService;
import org.example.Service.XMLFileService.AbstractXMLService;
import org.example.Service.XMLFileService.StudentXMLService;
import org.example.Validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private AbstractService<String, Student> studentService;
    private AbstractXMLService<String, Student> studentXMLService;
    @Before
    public void SetUp()throws Exception {
        StudentValidator studentValidator = new StudentValidator();
        StudentFileRepo studentFileRepository = new StudentFileRepo("students.txt", studentValidator);
        StudentXMLRepo studentXMLRepo=new StudentXMLRepo(studentValidator,"students.xml");
        studentService= new StudentService(studentFileRepository);
        studentXMLService=new StudentXMLService(studentXMLRepo);
    }


    @Test
    public void testAddStudentTXTFileService() {
        boolean thrown=false;
        String name="isabela";
        try {
            studentService.add(new String[]{"1", name, "933", "isabelafitero@gmail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertFalse(thrown);
        assertEquals(studentService.getById("1").getNume(), name);
    }

    @Test
    public void testAddStudentXMLFileService() {
        boolean thrown=false;
        String name="isabela";
        try {
            studentXMLService.add(new String[]{"1", name, "933", "isabelafitero@gmail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertFalse(thrown);
        Student student=studentXMLService.findOne("1");
        assertEquals(student.getNume(),"isabela");
    }
}