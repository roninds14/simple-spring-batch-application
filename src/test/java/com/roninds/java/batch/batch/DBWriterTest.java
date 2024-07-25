package com.roninds.java.batch.batch;

import com.roninds.java.batch.model.User;
import com.roninds.java.batch.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class DBWriterTest {

    @Mock
    private UserRepository userRepository;

    DBWriter writer;

    @BeforeEach
    void setUp() {
        setWriter(
                Mockito.mock(
                        DBWriter.class,
                        Mockito
                                .withSettings()
                                .useConstructor()
                                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
                )
        );

        getWriter().setUserRepository(getUserRepository());
    }

    @Test
    void writerSimpleTest() throws Exception {
        //Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User());

        Mockito.when(getUserRepository().saveAll(userList)).thenReturn(null);

        //Act
        getWriter().write(userList);

        ArgumentCaptor<List<User>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(getUserRepository()).saveAll(argumentCaptor.capture());

        List<User> users = argumentCaptor.getValue();

        //Assert
        Assertions.assertEquals(1, users.size());
    }
}
