package com.roninds.java.batch.model;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class UserTest {

    private static final String DUMMY_STRING = "dummyString";
    private static final Integer DUMMY_INTEGER = 1;

    @Test
    void noArgsConstructorTest() {
        //Arrange
        User user = new User();

        //Act
        user.setDept(DUMMY_STRING);
        user.setId(DUMMY_INTEGER);
        user.setSalary(DUMMY_INTEGER);
        user.setName(DUMMY_STRING);

        //Assert
        Assertions.assertSame(DUMMY_INTEGER, user.getId());
        Assertions.assertSame(DUMMY_INTEGER, user.getSalary());
        Assertions.assertSame(DUMMY_STRING, user.getName());
        Assertions.assertSame(DUMMY_STRING, user.getDept());
    }

    @Test
    void allArgsConstructorTest() {
        //Arrange
        //Act
        User user = new User(DUMMY_INTEGER, DUMMY_STRING, DUMMY_STRING, DUMMY_INTEGER);

        //Assert
        Assertions.assertSame(DUMMY_INTEGER, user.getId());
        Assertions.assertSame(DUMMY_INTEGER, user.getSalary());
        Assertions.assertSame(DUMMY_STRING, user.getName());
        Assertions.assertSame(DUMMY_STRING, user.getDept());
    }

    @Test
    void toStringTest() {
        //Arrange
        String expectedString = "User(id=1, name=dummyString, dept=dummyString, salary=1)";

        //Act
        User user = new User(DUMMY_INTEGER, DUMMY_STRING, DUMMY_STRING, DUMMY_INTEGER);

        //Assert
        Assertions.assertEquals(expectedString, user.toString());
    }
}
