package cn.zzk.springtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @DisplayName("验证测试")
    @Test
    void testVerify() {
        List<String> list = mock(List.class);
        list.add("one");
        list.add("two");
        list.add("two");
        list.clear();

        //验证调用 one 一次
        verify(list).add("one");
        //验证调用 two 两次
        verify(list, times(2)).add("two");
        //验证 three 一次没调用
        verify(list, never()).add("three");

        verify(list).clear();


    }

    /**
     * OngoingStubbing<T> thenReturn(T value) 方法 :
     * 1. 有返回值方法 --> 默认返回 null,空集合，默认值。例如 int/Integer 返回 0, boolean/Boolean 返回 false
     * 2. stubbing 可以被覆盖，但一般不会进行覆盖
     * 3. 一旦 stubbing , 不管调用多少次,方法都会永远返回 stubbing 的值
     * 4. 当对同一个方法进行多次 stubbing,最后一次最重要
     */
    @DisplayName("验证打桩")
    @Test
    void testSubbing() {
        List<String> list = mock(ArrayList.class);

        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenThrow(new RuntimeException());

        assertEquals("first", list.get(0));
        assertThrows(RuntimeException.class, () -> list.get(1));
        assertNull(list.get(999));
        verify(list).get(0);


        //"根据调用顺序设置不同的 stubbing"
        List<String> stubbing = mock(ArrayList.class);

        when(stubbing.get(0))
                .thenReturn("one")
                .thenReturn("two")
                .thenThrow(new RuntimeException());

        assertEquals("one", stubbing.get(0));
        assertEquals("two", stubbing.get(0));
        assertThrows(RuntimeException.class, () -> stubbing.get(0));
    }

    @DisplayName("验证参数匹配")
    @Test
    void testArgumentMatcher() {
        List<String> list = mock(ArrayList.class);

        when(list.get(anyInt())).thenReturn("hello");

        assertEquals("hello", list.get(888));
    }

    @DisplayName("验证调用顺序")
    @Test
    void testInOrder() {
        List<String> first = mock(ArrayList.class);
        List<String> second = mock(ArrayList.class);

        first.add("a");
        second.add("b");

        // 验证先调用 a , 再调用 b
        InOrder inOrder = inOrder(first, second);
        inOrder.verify(first).add("a");
        inOrder.verify(second).add("b");
    }

    @DisplayName("验证对象未产生交互")
    @Test
    void testNeverHappened() {
        List<String> first = mock(ArrayList.class);
        List<String> second = mock(ArrayList.class);

        verifyNoInteractions(first, second);
    }


    /**
     * 若 spy 的是对象,则拷贝对象的数据。
     * 若 spy 的是类 : 若类有无参构造器,则创建该类的实例;若类没有无参构造器，则 mock 该类
     */
    @DisplayName("验证 Spy -- 监视")
    @Test
    void testSpy() {
        List<String> list = new ArrayList<>();
        List<String> spy = spy(list);
        spy.add("a");
        spy.add("b");

        assertEquals("a", spy.get(0));
        assertEquals("b", spy.get(1));

        assertEquals(2, spy.size());


        when(spy.size()).thenReturn(100);
        assertEquals(100, spy.size());
    }

    @DisplayName("查看是否 mock")
    @Test
    void checkMock() {
        List<String> mock = mock(List.class);

        assertTrue(mockingDetails(mock).isMock());
    }
}
