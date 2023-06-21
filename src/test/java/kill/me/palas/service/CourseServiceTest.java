package kill.me.palas.service;

import kill.me.palas.models.Course;
import kill.me.palas.repositories.CourseRepository;
import kill.me.palas.services.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/appconfig-data.xml", "file:src/main/webapp/WEB-INF/appconfig-root.xml"})
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private Course course;
    @InjectMocks
    private CourseService courseService;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockCreation(){
        assertNotNull(course);
        assertNotNull(courseRepository);
        assertNotNull(courseService);
    }

    @Test
    public void findOne_Return_True() {
//        given(courseRepository.)
    }
}