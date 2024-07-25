package com.roninds.java.batch.controller;

import com.roninds.java.batch.service.LoadService;
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
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class LoadControllerTest {

    @Mock
    private LoadService loadService;

    private LoadController controller;

    @BeforeEach
    void setUp() {
        setController(
                Mockito.mock(
                        LoadController.class,
                        Mockito.withSettings()
                                .useConstructor()
                                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
                )
        );

        getController().setLoadService(getLoadService());
    }

    @Test
    void loadSimpleTest() throws Exception {
        // Arrange
        BatchStatus expectedStatus = BatchStatus.COMPLETED;
        Mockito.when(getLoadService().load()).thenReturn(expectedStatus);

        // Act
        BatchStatus actualStatus = getController().load();

        // Assert
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void loadThrowError() throws JobExecutionException {
        //Arrange
        Mockito.when(getLoadService().load()).thenThrow(new JobInstanceAlreadyCompleteException("Test Exception"));

        //Act
        JobExecutionException exception = Assertions.assertThrows(JobExecutionException.class, () -> getController().load());

        //Assert
        Assertions.assertEquals("Test Exception", exception.getMessage());
    }
}
