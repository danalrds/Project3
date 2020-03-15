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

import static org.junit.Assert.*;

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
        String name="bob";
        try {
            studentService.add(new String[]{"1", name, "933", "bob@nomail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertFalse(thrown);
        assertEquals(studentService.getById("1").getNume(), name);
    }
    @Test
    public void testAddStudentNoName(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "", "933", "bob@nomail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testAddStudentNULLName(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", null, "933", "bob@nomail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testAddStudentNoEmail(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "bob", "933", "", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testAddStudentNULLEmail(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "bob", "933", null, "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testAddStudentNegativeGroup(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "bob", "-1", "bob@nomail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testAddStudentNoCoordinator(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "bob", "933", "bob@nomail.com", ""});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testAddStudentNULLCoordinator(){
        boolean thrown=false;
        try {
            studentService.add(new String[]{"3", "bob", "933", "bob@nomail.com", null});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertTrue(thrown);
    }

}