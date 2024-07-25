package com.roninds.java.batch.batch;

import com.roninds.java.batch.model.User;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class ProcessorTest {

    Processor processor;

    @BeforeEach
    void setUp() {
        setProcessor(
                Mockito.mock(
                        Processor.class,
                        Mockito
                                .withSettings()
                                .useConstructor()
                                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("deptName")
    void processSimpleTest(String deptCode, String deptName) throws Exception {
        //Arrange
        User user = new User();
        user.setDept(deptCode);

        //Act
        User result = getProcessor().process(user);

        //Assert
        Assertions.assertEquals(deptName, result.getDept());
    }

    static Stream<Arguments> deptName(){
        return Stream.of(
                Arguments.of( "001", "Technology"),
                Arguments.of( "002", "Operations"),
                Arguments.of( "003", "Accounts")
        );
    }

}
