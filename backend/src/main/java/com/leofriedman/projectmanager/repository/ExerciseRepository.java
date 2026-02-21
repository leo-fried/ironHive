package com.leofriedman.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leofriedman.projectmanager.model.*;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
}