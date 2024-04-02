package com.example.demo.dao;

import com.example.demo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);
}
