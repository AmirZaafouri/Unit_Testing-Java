package tn.esprit.spring.services;

import nonapi.io.github.classgraph.utils.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito
@DisplayName("RegestriationImpl Unit Test")
class RegistrationServicesImplTest {
    @Mock
    private IRegistrationRepository registrationRepository;
    @Mock
    private ISkierRepository skierRepository;
    @Mock
    private ICourseRepository courseRepository;
    @InjectMocks
    RegistrationServicesImpl registrationServicesImpl;
    private Registration testRegistration;
    private Skier testSkier;
    private Course testCourse;

    @BeforeEach
    void setUp() {
        this.testRegistration = Registration.builder()
                .numRegistration(152L)
                .numWeek(5)
                .build();
        this.testSkier = Skier.builder()
                .numSkier(152L)
                .lastName("Lin")
                .firstName("Frank")
                .city("Suisse")
                .dateOfBirth(LocalDate.of(1999,05,15))
                .build();
        this.testCourse = Course.builder()
                .numCourse(1336L)
                .typeCourse(TypeCourse.INDIVIDUAL)
                .price(12.5F)
                .build();
    }


    @Nested // allow to run class inside class to regrouped
    @DisplayName("Create Registriation Tests")
    //Hedhom nest3mlouha llnidham kif yabda 3andek Akther min test 3la nafess scenario
    class AddRegistration{

        @Test
        @DisplayName("Shouled we Create new Registriation")
        void shouldAddRegistrationSuccefully(){
            //Given
            Long numSkier = 15L;
            when(skierRepository.findById(numSkier))
                    .thenReturn(Optional.of(testSkier));
            //3lech we don't Mock the setSkier !! khater simple Set by Ali Bouali
            //!! why return the value we ntestiw 3lih ba3ed juste to make sure the value don't change during the execution of all methodes
            when(registrationRepository.save(any(Registration.class)))
                    .thenReturn(testRegistration);
            // When
            final Registration registration=  registrationServicesImpl.addRegistrationAndAssignToSkier(testRegistration,numSkier);
            //Then
            assertNotNull(registration);
            assertEquals(testRegistration,registration);
            verify(skierRepository,times(1)).findById(numSkier);
            verify(registrationRepository,times(1)).save(testRegistration);
            // Verify that the Skier was correctly set before we save
            verify(registrationRepository).save(argThat(reg ->
                    reg.getSkier() != null && reg.getSkier().getNumSkier().equals(testSkier.getNumSkier())
            ));
        }

    }
    @Nested
    @DisplayName("Assign Registriation Tests")
    class AssignRegistrationRegistration{
        @Test
        @DisplayName("Should we Throw Entity Exception when Registration Not Found")
        void shouldThrowEntityNotFoundExceptionWhenRegistrationNotFound(){
            //Given
            final Long numRegistration = 15L;
            final Long numCource = 213L;
            when(registrationRepository.findById(numRegistration)).thenReturn(Optional.empty());
            //When & Then
            final EntityNotFoundException exception = assertThrows( EntityNotFoundException.class,
                    ()->registrationServicesImpl.assignRegistrationToCourse(numRegistration,numCource) );

            assertEquals(exception.getMessage(),"No Regestiration was Found "+ numRegistration);
            verify(courseRepository,times(0)).findById(numCource);
            verify(registrationRepository,times(0)).save(testRegistration);
        }
        @Test
        @DisplayName("Should we Assign Registration To Course")
        void shouldAssignRegistrationToCourse(){
            //Given
            final Long numRegistration = 15L;
            final Long numCource = 213L;
            //When
            when(registrationRepository.findById(numRegistration)).thenReturn(Optional.of(testRegistration));
            when(courseRepository.findById(numCource)).thenReturn(Optional.of(testCourse));
            when(registrationRepository.save(any(Registration.class))).thenReturn(testRegistration);

            Registration registration = registrationServicesImpl.assignRegistrationToCourse(numRegistration,numCource);
            //Then
            assertEquals(registration,testRegistration);
            verify(registrationRepository,times(1)).findById(numRegistration);
            verify(courseRepository,times(1)).findById(numCource);
            verify(registrationRepository).save(argThat(reg -> reg.getCourse() != null && reg.getCourse().getNumCourse().equals(testCourse.getNumCourse())));

        }


    }



}
