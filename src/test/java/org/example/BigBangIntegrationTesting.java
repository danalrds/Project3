package org.example;

import org.example.Domain.Nota;
import org.example.Domain.Student;
import org.example.Domain.TemaLab;
import org.example.Exceptions.ValidatorException;
import org.example.Repository.TxtFileRepository.NotaFileRepo;
import org.example.Repository.TxtFileRepository.StudentFileRepo;
import org.example.Repository.TxtFileRepository.TemaLabFileRepo;
import org.example.Repository.XMLFileRepository.StudentXMLRepo;
import org.example.Service.TxtFileService.AbstractService;
import org.example.Service.TxtFileService.NotaService;
import org.example.Service.TxtFileService.StudentService;
import org.example.Service.TxtFileService.TemaLabService;
import org.example.Validator.NotaValidator;
import org.example.Validator.StudentValidator;
import org.example.Validator.TemaLabValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigBangIntegrationTesting {
    /**
     * Rigorous Test :-)
     */
    private AbstractService<String, Student> studentService;
    private AbstractService<Integer, TemaLab> assignmentService;
    private TemaLabValidator assignmentValidator;
    private NotaValidator gradeValidator;
    private AbstractService<Integer, Nota> gradeService;

    @Before
    public void SetUp() throws Exception {
        //Student
        StudentValidator studentValidator = new StudentValidator();
        StudentFileRepo studentFileRepository = new StudentFileRepo("students.txt", studentValidator);
        studentService = new StudentService(studentFileRepository);
        //Assignment
        assignmentValidator = new TemaLabValidator();
        TemaLabFileRepo assignmentRepository = new TemaLabFileRepo("assignments.txt", assignmentValidator);
        assignmentService = new TemaLabService(assignmentRepository);
        //Grade
        gradeValidator = new NotaValidator();
        NotaFileRepo gradeRepository = new NotaFileRepo("grades.txt", gradeValidator);
        gradeService = new NotaService(gradeRepository);
    }

    @Test
    public void testAddStudent() {
        boolean thrown = false;
        int beforeSize = studentService.size();
        String name = "bob";
        try {
            studentService.add(new String[]{"1", name, "933", "bob@nomail.com", "teacher"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(studentService.getById("1").getNume(), name);
        assertEquals(beforeSize + 1, studentService.size());
    }

    @Test
    public void testAddAssignment() {
        int beforeSize = assignmentService.size();
        String description = "description1";
        boolean thrown = false;
        try {
            assignmentService.add(new String[]{"1", description, "14", "10"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(assignmentService.size(), beforeSize + 1);
        TemaLab assignment = assignmentService.getById(1);
        assertEquals(assignment.getDescriere(), description);
        assertEquals(assignment.getTermenLimita(), 14);
        assertEquals(assignment.getSaptammanaPredarii(), 10);
    }

    @Test
    public void testAddGrade() {
        boolean thrown = false;
        int beforeSize = gradeService.size();
        try {
            gradeService.add(new String[]{"1", "1", "1", "10", "2020-03-04 15:00:00"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
        assert (gradeService.getById(1).getTemaLabId().equals(1));
        assert (gradeService.getById(1).getValoare() == 10);
        assertEquals(beforeSize + 1, gradeService.size());
    }

    @Test
    public void testAddAllBigBangIntegrated() {
        boolean thrown = false;
        int beforeSizeStudentService = studentService.size();
        int beforeSizeAssignmentService = assignmentService.size();
        int beforeSizeGradeService = gradeService.size();
        try {
            studentService.add(new String[]{"2", "dita", "933", "dita@nomail.com", "teacher"});
            assignmentService.add(new String[]{"2", "desc", "14", "10"});
            gradeService.add(new String[]{"2", "2", "2", "10", "2020-04-03 19:00:00"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
        assert (studentService.getById("2").getNume().equals("dita"));
        assert (assignmentService.getById(2).getDescriere().equals("desc"));
        assert (gradeService.getById(2).getValoare() == 10);
        assertEquals(beforeSizeStudentService + 1, studentService.size());
        assertEquals(beforeSizeAssignmentService + 1, assignmentService.size());
        assertEquals(beforeSizeGradeService + 1, gradeService.size());
    }
}