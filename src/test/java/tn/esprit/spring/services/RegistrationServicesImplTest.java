package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        this.testRegistration = Registration.builder()
                .numRegistration(152L)
                .numWeek(5)
                .build();
    }


    @Nested // allow to run class inside class to regrouped
    @DisplayName("Create Registriation Tests")
    class AddRegistration{

        @Test
        @DisplayName("Shouled we Create new Registriation")
        void shouldAddRegistrationSuccefully(){
            //Given
            Registration registration;
            Long numSkier = 15L;
            // When
            when()
            //Then

        }
    }



}
