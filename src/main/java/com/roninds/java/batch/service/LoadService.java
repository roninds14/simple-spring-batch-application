package com.roninds.java.batch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoadService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public BatchStatus load () throws
            JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        Map<String, JobParameter> map = new HashMap<>();
        map.put("time", new JobParameter(System.currentTimeMillis()));

        JobParameters parameters = new JobParameters(map);

        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExecution: " + jobExecution.getStatus());

        log.info("Batch is Running...");
        while (jobExecution.isRunning()){
            log.info("...");
        }

        return jobExecution.getStatus();
    }
}
