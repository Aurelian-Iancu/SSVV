package ssvv.lab1;

import domain.Student;
import domain.Tema;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase
{
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";

    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    //TC1
    public void testAddStudentGoodId(){
        Student studentGoodId = new Student("1001", "maria", 933, "maria@email.com");
        Assertions.assertNull(service.addStudent(studentGoodId));
    }

    //TC2
    public void testAddStudentNullId(){
        Student studentNullId = new Student(null, "ana", 933, "ana@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentNullId);
        });
    }

    //TC3
    public void testAddStudentEmptyId(){
        Student studentEmptyId = new Student("", "ana", 933, "ana@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentEmptyId);
        });
    }

    //TC4
    public void testAddStudentGoodName(){
        Student studentGoodName = new Student("1003", "Andrei", 933, "maria@email.com");

        Assertions.assertNull(service.addStudent(studentGoodName));
    }

    //TC5
    public void testAddStudentNullName(){
        Student studentEmptyName = new Student("1003", null, 933, "maria@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentEmptyName);
        });
    }

    //TC6
    public void testAddStudentEmptyName(){
        Student studentEmptyName = new Student("1003", "", 933, "maria@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentEmptyName);
        });
    }

    //TC7
    public void testAddStudentGoodEmail(){
        Student studentGoodEmail = new Student("1004", "Andrei", 933, "maria@email.com");

        Assertions.assertNull(service.addStudent(studentGoodEmail));
    }

    //TC8
    public void testAddStudentNullEmail(){
        Student studentNullEmail = new Student("1003", "Andrei", 933, null);

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentNullEmail);
        });
    }

    //TC9
    public void testAddStudentEmptyEmail(){
        Student studentEmptyEmail = new Student("1003", "Andrei", 933, "");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentEmptyEmail);
        });
    }

    //TC10
    public void testAddStudentGoodGroup(){
        Student studentGoodGroup = new Student("1005", "Andrei", 933, "maria@email.com");

        Assertions.assertNull(service.addStudent(studentGoodGroup));
    }

    //TC11
    public void testAddStudentNegativeGroup(){
        Student studentNegativeGroup = new Student("1005", "Andrei", -1, "maria@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentNegativeGroup);
        });
    }

    //TC12
    public void testAddStudent0Group(){
        Student student0Group = new Student("1006", "Andrei", 0, "maria@email.com");

        Assertions.assertNull(service.addStudent(student0Group));
    }

    //TC13
    public void testAddStudentMAX_INTGroup(){
        Student studentMAX_INTGroup = new Student("1007", "Andrei", Integer.MAX_VALUE, "maria@email.com");

        Assertions.assertNull(service.addStudent(studentMAX_INTGroup));
    }

    //TC14
    public void testAddStudentMAX_INT_MINUS_1Group(){
        Student studentMAX_INT_MINUS_1Group = new Student("1008", "Andrei", Integer.MAX_VALUE - 1, "maria@email.com");

        Assertions.assertNull(service.addStudent(studentMAX_INT_MINUS_1Group));
    }

    //TC15
    public void testAddStudentMAX_INT_PLUS_1Group(){
        Student studentMAX_INT_PLUS_1Group = new Student("1009", "Andrei", Integer.MAX_VALUE + 1, "maria@email.com");

        assertThrows(ValidationException.class, () -> {
            service.addStudent(studentMAX_INT_PLUS_1Group);
        });
    }

    //TC16
    public void testAddStudentWithDifferentIds(){
        Student student1 = new Student("1009", "Andrei", 933, "maria@email.com");
        Student student2 = new Student("1010", "Andrei", 933, "maria@email.com");

        Assertions.assertNull(service.addStudent(student1));
        Assertions.assertNull(service.addStudent(student2));
    }

    ////////////////////////////////////WBT TestCases
    public void testAddAssignmentEmptyId(){
        Tema temaEmptyId = new Tema("", "descriere", 12, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(temaEmptyId);
        });
    }

    public void testAddAssignmentEmptyDescription(){
        Tema temaDescriptionId = new Tema("1", "", 12, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(temaDescriptionId);
        });
    }

    public void testAddAssignmentWrongDeadline(){
        Tema temaWrongDeadline = new Tema("1", "descriere", 15, 12);

        assertThrows(ValidationException.class, () -> {
            service.addTema(temaWrongDeadline);
        });
    }

    public void testAddAssignmentWrongPrimire(){
        Tema temaWrongDeadline = new Tema("1", "descriere", 12, 15);

        assertThrows(ValidationException.class, () -> {
            service.addTema(temaWrongDeadline);
        });
    }

    public void testAddGood(){
        Tema temaGoodAssignment = new Tema("125", "descriere", 12, 12);

        Assertions.assertNull(service.addTema(temaGoodAssignment));
    }



}