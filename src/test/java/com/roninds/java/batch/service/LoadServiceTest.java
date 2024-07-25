package com.roninds.java.batch.service;

import com.roninds.java.batch.controller.LoadController;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class LoadServiceTest {

    @Mock
    JobLauncher jobLauncher;

    @Mock
    Job job;

    LoadService service;

    @BeforeEach
    void setUp() {
        setService(
                Mockito.mock(
                        LoadService.class,
                        Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS)
                )
        );

        getService().setJob(getJob());
        getService().setJobLauncher(getJobLauncher());
    }

    @Test
    void loadSimpleTest() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        //Arrange
        BatchStatus expectedStatus = BatchStatus.COMPLETED;

        JobExecution jobExecutionSpy = Mockito.spy(new JobExecution(1L));

        Mockito.when(jobExecutionSpy.isRunning())
                .thenReturn(true)
                .thenReturn(false);

        jobExecutionSpy.setStatus(expectedStatus);

        Mockito.when(getJobLauncher().run(Mockito.any(), Mockito.any())).thenReturn(jobExecutionSpy);

        //Act
        BatchStatus result = getService().load();

        //Assert
        Assertions.assertEquals(expectedStatus, result);
    }
}
