package com.soen490.cms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class JUnitTest {

    FirstClass firstClass = mock(FirstClass.class);

    @Test
    public void helloWorldMockitoTest(){
        when(firstClass.helloWorld()).thenReturn("Hello World!");
        assertEquals("Hello World!", firstClass.helloWorld());
    }

    @Test
    public void helloWorldTest(){
        FirstClass test = new FirstClass();
        assertEquals("Hello World!", test.helloWorld());
    }
}
