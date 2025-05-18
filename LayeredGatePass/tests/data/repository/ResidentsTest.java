package data.repository;

import data.model.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ResidentsTest {

    private ResidentRepository residents;

    @BeforeEach
    public void setUp() {
        residents = new Residents();
    }

    @Test
    public void beforeCreation_countIsZeroTest() {
        assertEquals(0, residents.count());
    }

    @Test
    public void registerResident_countIsOneTest(){
        Resident residents1 = new Resident();
        residents.save(residents1);

        assertEquals(1, residents.count());
    }

    @Test
    public void registerResident_countIsTwoTest(){
        Resident residents1 = new Resident();
        Resident residents2 = new Resident();

        residents.save(residents1);
        residents.save(residents2);
        assertEquals(2, residents.count());
    }

    @Test
    public void registerResident_countIsOne_findByIdTest(){
        Resident residents1 = new Resident();
        Resident foundResident = residents.save(residents1);
        Resident residentFound = residents.findById(foundResident.getId()).get();

        assertEquals(foundResident, residentFound);
    }

    @Test
    public void savedTwoResidents_updateResidentWithId_countIsOneTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        assertEquals(1, residents.count());

        Resident secondResidents = new Resident();
        secondResidents.setId(firstResidents.getId());
        secondResidents.setFullName("Moses Idowu");
        residents.save(secondResidents);

        assertEquals(1, residents.count());
        assertEquals(1, firstResidents.getId());
        assertEquals("Moses Idowu", residents.findById(firstResidents.getId()).get().getFullName());

    }

    @Test
    public void savedFourResidents_updateResidentOneAndThreeWithId_countIsTwoTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        assertEquals(1, residents.count());

        Resident secondResidents = new Resident();
        secondResidents.setId(firstResidents.getId());
        secondResidents.setFullName("Moses Idowu");
        residents.save(secondResidents);

        assertEquals(1, residents.count());
        assertEquals(1, secondResidents.getId());
        assertEquals("Moses Idowu", residents.findById(firstResidents.getId()).get().getFullName());

        Resident thirdResident = new Resident();
        residents.save(thirdResident);
        assertEquals(2, residents.count());

        Resident fourthResident = new Resident();
        fourthResident.setId(thirdResident.getId());
        fourthResident.setFullName("MD Empire");
        residents.save(fourthResident);

        assertEquals(2, residents.count());
        assertEquals(2, fourthResident.getId());
        assertEquals("MD Empire", residents.findById(thirdResident.getId()).get().getFullName());
    }

    @Test
    public void savedOneResidents_deleteResidentWithId_countIsZeroTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        firstResidents.setFullName("Moses Idowu");
        assertEquals(1, residents.count());

        residents.deleteById(firstResidents.getId());
        assertEquals(0, residents.count());
    }

    @Test
    public void savedTwoResidents_findTwoResidentWithId_countIsTwoTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        firstResidents.setFullName("MD Empire");
        firstResidents.setId(firstResidents.getId());
        assertEquals(1, residents.count());
        assertEquals(1, residents.findById(firstResidents.getId()).get().getId());

        Resident secondResidents = new Resident();
        secondResidents.setId(secondResidents.getId());
        secondResidents.setFullName("Moses Idowu");
        residents.save(secondResidents);

        assertEquals(2, residents.count());
        assertEquals(2, residents.findById(secondResidents.getId()).get().getId());

    }

    @Test
    public void savedOneResident_findByFullName_countIsOneTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        firstResidents.setFullName("Moses Idowu");
        assertEquals(1, residents.count());
        assertEquals("Moses Idowu", residents.findByFullName("Moses Idowu").get(0).getFullName());

        Resident secondResidents = new Resident();
        residents.save(secondResidents);
        secondResidents.setFullName("MD Empire");
        assertEquals(2, residents.count());
        assertEquals("MD Empire", residents.findByFullName("MD Empire").get(1).getFullName());
    }

    @Test
    public void savedOneResident_findAllResidents_countIsOneTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        firstResidents.setFullName("Moses Idowu");
        assertEquals(1, residents.count());
        assertEquals("Moses Idowu", residents.findAll().get(0).getFullName());
    }

    @Test
    public void savedOneResident_checkIfResidentExistsById_returnTrueTest(){
        Resident firstResidents = new Resident();
        residents.save(firstResidents);
        firstResidents.setFullName("Moses Idowu");

        assertTrue(residents.existsById(firstResidents.getId()));
    }
}
