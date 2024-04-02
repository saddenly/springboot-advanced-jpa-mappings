package com.example.demo;

import com.example.demo.dao.AppDAOImpl;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAOImpl appDAO) {
        return runner -> findInstructor(appDAO);
    }

    private void findInstructor(AppDAOImpl appDAO) {
        int theId = 1;
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
