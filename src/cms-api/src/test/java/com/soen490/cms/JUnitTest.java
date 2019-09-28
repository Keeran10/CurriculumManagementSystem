package com.soen490.cms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class JUnitTest {

    FirstClass firstClass = mock(FirstClass.class);

    @org.junit.Test
    public void helloWorldTest(){
        when(firstClass.helloWorld()).thenReturn("Hello World!");
        assertEquals("Hello World!", firstClass.helloWorld());
    }
}
