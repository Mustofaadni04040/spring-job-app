package com.exercise.jobapp.job.impl;

import com.exercise.jobapp.job.Job;
import com.exercise.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        return jobs.remove(getJobById(id));
    }

    @Override
    public boolean updateJob(Long id, Job jobToUpdate) {
        Job existingJob = getJobById(id);
        if (existingJob != null) {
            existingJob.setTitle(jobToUpdate.getTitle());
            existingJob.setDescription(jobToUpdate.getDescription());
            existingJob.setMinSalary(jobToUpdate.getMinSalary());
            existingJob.setMaxSalary(jobToUpdate.getMaxSalary());
            existingJob.setLocation(jobToUpdate.getLocation());
            return true;
        }
        return false;
    }
}
