package com.leofriedman.projectmanager.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.leofriedman.projectmanager.dto.ExerciseEntry;
import com.leofriedman.projectmanager.model.Exercise;
import com.leofriedman.projectmanager.repository.ExerciseRepository;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;
    
    public ExerciseService(ExerciseRepository repository)
    {
        this.repository = repository;
    }

    public List<Exercise> getAllExercises()
    {
        return repository.findAll();
    }
    public Exercise getExerciseById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise with ID: " + id + " not found"));
    }
    public Exercise createExercise(ExerciseEntry exerciseEntry)
    {
        Exercise exercise = new Exercise();
        exercise.setName(exerciseEntry.getName());
        exercise.setIntensity(exerciseEntry.getIntensity());
        exercise.setReps(exerciseEntry.getReps());
        exercise.setWeight(exerciseEntry.getWeight());
        return repository.save(exercise);
    }
    public Exercise updateExercise(Long id, Exercise updatedExercise)
    {
        Exercise exercise = getExerciseById(id);
        exercise.setName(updatedExercise.getName());
        exercise.setIntensity(updatedExercise.getIntensity());
        exercise.setReps(updatedExercise.getReps());
        exercise.setWeight(updatedExercise.getWeight());
        return repository.save(exercise);
    }
    public void deleteExercise(Long id)
    {
        repository.deleteById(id);
    }
}
