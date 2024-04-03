package com.example.demo;

import com.example.demo.dao.AppDAOImpl;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAOImpl appDAO) {
        return runner -> deleteCourseAndReviews(appDAO);
    }

    private void deleteCourseAndReviews(AppDAOImpl appDAO) {
        int id = 4;
        System.out.println("Deleting course id: " + id);

        appDAO.deleteCourseById(id);

        System.out.println("Done");
    }

    private void retrieveCourseAndReviews(AppDAOImpl appDAO) {
        int id = 4;
        Course course = appDAO.findCourseAndReviewsById(id);

        System.out.println(course);

        System.out.println(course.getReviews());

        System.out.println("Done");
    }

    private void createCourseAndReviews(AppDAOImpl appDAO) {
        Course course = new Course("Pacman - how to score a million score");
        course.addReview(new Review("Nice"));
        course.addReview(new Review("Bad"));
        course.addReview(new Review("Good"));
        course.addReview(new Review("Awful"));
        course.addReview(new Review("Poor"));
        course.addReview(new Review("Elite"));

        appDAO.save(course);
    }

    private void deleteCourse(AppDAOImpl appDAO) {
        int theId = 1;
        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done");
    }

    private void updateCourse(AppDAOImpl appDAO) {
        int theId = 2;
        System.out.println("Finding a course id: " + theId);
        Course course = appDAO.findCourseById(theId);

        System.out.println("Updating course id: " + theId);
        course.setTitle("Coding Masterclass");

        appDAO.update(course);
        System.out.println("Done");
    }

    private void updateInstructor(AppDAOImpl appDAO) {
        int theId = 3;
        System.out.println("Finding an instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Done");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAOImpl appDAO) {
        int theId = 4;
        System.out.println("Finding an instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done");
    }

    private void findCoursesForInstructor(AppDAOImpl appDAO) {
        int theId = 4;
        System.out.println("Finding an instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("Finding courses for an instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done");
    }

    private void findInstructorWithCourses(AppDAOImpl appDAO) {
        int theId = 4;
        System.out.println("Finding an instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done");
    }

    private void createInstructorWithCourses(AppDAOImpl appDAO) {
        Instructor tempInstructor =
                new Instructor("Susan", "Patrick", "susan@gmail.com");
        InstructorDetail instructorDetail =
                new InstructorDetail("https://youtube.com", "Singing");

        tempInstructor.setInstructorDetail(instructorDetail);

        Course tempCourse = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");
        tempInstructor.add(tempCourse);
        tempInstructor.add(tempCourse2);

        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done");
    }

    private void deleteInstructorDetail(AppDAOImpl appDAO) {
        int theId = 3;
        System.out.println("Deleting instructorDetail id: " + theId);

        appDAO.deleteInstructionDetailById(theId);

        System.out.println("Done");
    }

    private void findInstructorDetail(AppDAOImpl appDAO) {
        int theId = 2;
        System.out.println("Finding instructorDetail id: " + theId);

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        System.out.println("the associated instructorDetail only: " + tempInstructorDetail.getInstructor());
        System.out.println("Done");
    }

    private void deleteInstructor(AppDAOImpl appDAO) {
        int theId = 4;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done");
    }

    private void findInstructor(AppDAOImpl appDAO) {
        int theId = 2;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAOImpl appDAO) {
        Instructor tempInstructor =
                new Instructor("Madhu", "Patel", "madhu@gmail.com");
        InstructorDetail instructorDetail =
                new InstructorDetail("https://youtube.com", "Guitar");
        tempInstructor.setInstructorDetail(instructorDetail);
        System.out.println("Saving the instructor: " + tempInstructor);
        appDAO.save(tempInstructor);
    }
}
