package com.mjc.school.operation;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class OperationEngineTest {

    @Mock
    private OperationReader operationReader;

    @Mock
    private BaseController<?, ?, ?> mockController;

    @Mock
    private Command mockCommand;

    //@InjectMocks
    private OperationEngine operationEngine;

    /*@BeforeEach
    void setUp() {
        when(mockCommand.execute()).thenReturn(123L);
    }*/

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a list with your mock controller
        List<BaseController<?, ?, ?>> controllers = Collections.singletonList(mockController);

        // Manually create the OperationEngine instance with the required dependencies
        operationEngine = new OperationEngine(operationReader, controllers);

        when(mockCommand.execute()).thenReturn(123L);
    }

/*    @Test
    void execute_findsHandlerMethod() throws Exception {
        // Setup test method with annotation
       *//* Method method = TestController.class.getMethod("handleCreate", Long.class);
        TestController controller = new TestController();*//*

        // Test
        //operationEngine.execute(Operations.CREATE_NEWS, mockCommand);
        Method executeMethod = OperationEngine.class
                .getDeclaredMethod("execute", Operations.class, Command.class);
        executeMethod.setAccessible(true);

        executeMethod.invoke(operationEngine, Operations.CREATE_NEWS, mockCommand);

        // Verify via logs or side effects
        assertTrue(true); // Actual verification would check method invocation
    }*/

    @Test
    void prepareArguments_handlesCommandBody() throws Exception {
        Method prepareMethod = OperationEngine.class
                .getDeclaredMethod("prepareArguments", Method.class, Command.class);
        prepareMethod.setAccessible(true);

        Method handlerMethod = TestController.class.getMethod("handleCreate", Long.class);

        Object[] args = (Object[]) prepareMethod.invoke(
                operationEngine,
                handlerMethod,
                mockCommand
        );
        //Object[] args = operationEngine.prepareArguments(method, mockCommand);

        assertEquals(1, args.length);
        assertEquals(123L, args[0]);
    }

    // Test controller for reflection
    static class TestController {
        @CommandHandler(operation = "Create news")
        public void handleCreate(@CommandParam("id") Long id) {}
    }
}