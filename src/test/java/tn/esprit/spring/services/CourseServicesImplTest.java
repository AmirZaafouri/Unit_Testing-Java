package tn.esprit.spring.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import nonapi.io.github.classgraph.utils.Assert;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito
@DisplayName("ServiceImpl Unit Test")
class CourseServicesImplTest {
    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks // Injection by constructure , par propriéte , par setter => in order way
    private CourseServicesImpl courseServices;// This what we want to test
    private Course testCourse;

    @BeforeEach
    void setUp() {
        this.testCourse = Course.builder()
                .numCourse(1336L)
                .typeCourse(TypeCourse.INDIVIDUAL)
                .price(12.5F)
                .build();

    }

    @Test
    @DisplayName("Test_Value_Not_0")
    public void Test() {

    }

    @Test
    @Disabled
    public void Ignored() {

    }

    @Nested // allow to run class inside class to regrouped
    @DisplayName("Create Course Tests")
    class CreateCourceTests {
        @Test
        @DisplayName("shouled we create New Course")
        void shouldCreateCourcesSuccefully() {
            // Given
            Course newCourse = new Course();
            newCourse.setTypeCourse(TypeCourse.INDIVIDUAL);
            // When
            when(courseRepository.save(any(Course.class))).thenReturn(testCourse);
            // u can add to much when based on the function requirement
            final Course course = courseServices.addCourse(newCourse);
            // Then
            assertNotNull(course);
            assertEquals(12.5F, course.getPrice());
            assertEquals(TypeCourse.INDIVIDUAL, course.getTypeCourse());

            // Verify that repository.save() was called once
            verify(courseRepository, times(1)).save(any(Course.class));
        }

        @Test
        @DisplayName("Shouled return Null")
        void shouldReturnNull() {
            // Given
            Long numCourse = 15L;

            // When
            when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

            final Course course = courseServices.retrieveCourse(numCourse);
            // Then
            assertNull(course);

            // Verify that repository.save() was called once
            verify(courseRepository, times(1)).findById(anyLong());
        }
    }

}
