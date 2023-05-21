package kill.me.palas.service;

import kill.me.palas.services.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/appconfig-data.xml", "file:src/main/webapp/WEB-INF/appconfig-root.xml"})
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void findAll() {
        courseService.findAll();
    }
//
//    @Test
//    void findActiveAll() {
//    }
//
//    @Test
//    void findOne() {
//    }
//
//    @Test
//    void findTeacher() {
//    }
//
//    @Test
//    void findByName() {
//    }
}