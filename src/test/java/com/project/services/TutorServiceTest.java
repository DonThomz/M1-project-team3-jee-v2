package com.project.services;

import com.project.dao.TutorDao;
import com.project.models.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TutorServiceTest {

    private static Integer FakeTutorID = 0;

    @Mock
    TutorDao tutorDao;

    @BeforeEach
    void setup() {
        tutorDao = Mockito.mock(TutorDao.class);
    }

    @Test
    void findAll() {
        Tutor fakeTutor1 = createFakeTutor("daniel", "fake");
        Tutor fakeTutor2 = createFakeTutor("marie", "fake");
        List<Tutor> fakeTutors = Arrays.asList(fakeTutor1, fakeTutor2);

        // fake behavior
        when(tutorDao.findAll()).thenReturn(fakeTutors);

        TutorService service = new TutorService(tutorDao);
        List<Tutor> result = service.findAll();
        // assertions
        assertEquals(fakeTutors, result);
    }

    @Test
    void find() {
        Tutor fakeTutor = createFakeTutor(1, "daniel", "fake");

        // fake behavior
        when(tutorDao.find(1)).thenReturn(fakeTutor);

        TutorService service = new TutorService(tutorDao);
        Tutor result = service.find(1);
        // assertions
        assertEquals(fakeTutor, result);
    }

    @Test
    void save() {

        Tutor fakeTutor = createFakeTutor("daniel", "fake");

        // fake behavior
        doNothing().when(tutorDao).save(any(Tutor.class));

        TutorService service = new TutorService(tutorDao);
        service.save(fakeTutor);

    }

    @Test
    void saveAll() {
    }

    @Test
    void update() {

        Tutor fakeTutor = createFakeTutor("daniel", "fake");

        // fake behavior
        doNothing().when(tutorDao).save(any(Tutor.class));

        TutorService service = new TutorService(tutorDao);
        service.update(fakeTutor);

    }

    @Test
    void updateAll() {
    }

    @Test
    void findByEmail() {
    }

    private Tutor createFakeTutor(String firstname, String lastname) {
        Tutor tutor = new Tutor();
        tutor.setTutorId(FakeTutorID++);
        tutor.setFirstname(firstname);
        tutor.setLastname(lastname);
        tutor.setPassword("123");
        tutor.setEmail(firstname + "." + lastname + "@fake.net");
        tutor.setInternsByTutorId(new ArrayList<>());
        return tutor;
    }

    private Tutor createFakeTutor(int id, String firstname, String lastname) {
        Tutor tutor = createFakeTutor(firstname, lastname);
        tutor.setTutorId(id);
        return tutor;
    }

}