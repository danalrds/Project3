package org.example;

import org.example.Domain.Student;
import org.example.Domain.TemaLab;
import org.example.Exceptions.ValidatorException;
import org.example.Repository.TxtFileRepository.StudentFileRepo;
import org.example.Repository.TxtFileRepository.TemaLabFileRepo;
import org.example.Repository.XMLFileRepository.StudentXMLRepo;
import org.example.Service.TxtFileService.AbstractService;
import org.example.Service.TxtFileService.StudentService;
import org.example.Service.TxtFileService.TemaLabService;
import org.example.Service.XMLFileService.AbstractXMLService;
import org.example.Service.XMLFileService.StudentXMLService;
import org.example.Validator.StudentValidator;
import org.example.Validator.TemaLabValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class WhiteBoxTesting {
    /**
     * Rigorous Test :-)
     */
    private AbstractService<Integer, TemaLab> assignmentService;
    private TemaLabValidator assignmentValidator;
    @Before
    public void SetUp()throws Exception {

        assignmentValidator = new TemaLabValidator();
        TemaLabFileRepo assignmentRepository=new TemaLabFileRepo("assignments.txt", assignmentValidator);
        assignmentService= new TemaLabService(assignmentRepository);

    }

    @Test
    public void testAddAssignment()  {
        int beforeSize=assignmentService.size();
        String description="description1";
        boolean thrown=false;
        try {
            assignmentService.add(new String[]{"1", description, "14", "10"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrown=true;
        }
        assertFalse(thrown);
        assertEquals(assignmentService.size(), beforeSize + 1);
        TemaLab assignment=assignmentService.getById(1);
        assertEquals(assignment.getDescriere(), description);
        assertEquals(assignment.getTermenLimita(),14);
        assertEquals(assignment.getSaptammanaPredarii(), 10);
        boolean thrownValidator=false;
        try {
            assignmentValidator.validate(new TemaLab(1,description,14,10));
        } catch (ValidatorException e) {
            e.printStackTrace();
            thrownValidator=true;
        }
        assertFalse(thrownValidator);
    }

    @Test
    public void testAddAssignmentWhichIsNotValid()  {
        int beforeSize=assignmentService.size();
        String description="description2";
        boolean thrown=false;
        String exceptionMessage="";
        try {
            assignmentService.add(new String[]{"2", description, "15", "13"});
        } catch (ValidatorException e) {
            e.printStackTrace();
            exceptionMessage=e.getMessage();
            thrown=true;
        }
        assertTrue(thrown);
        assertEquals(assignmentService.size(), beforeSize );
        TemaLab assignment=assignmentService.getById(2);
        assertNull(assignment);
        assertEquals(exceptionMessage,"Termen limita invalid\n");
    }
}