package cn.zzk.kotlintest

import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MockkTest {

    @MockK
    lateinit var mother: Mother

    lateinit var kid: Kid

    @BeforeEach
    fun setup() {
        // relaxed : 被模拟的方法不需要全部设桩
        MockKAnnotations.init(this, relaxed = true)
        kid = Kid(mother)
    }

    @Test
    fun `通过 wantMany 测试 mockk 使用`() {
        every { mother.giveMoney() } returns 100
        kid.wantMoney()

        assertEquals(100, kid.money)

        //验证 mock 的对象是否被执行
        verify {
            mother.inform(any())
            mother.giveMoney()
        }

        //验证 inform() 方法的下一个一定是 giveMoney() 方法
        verifySequence {
            mother.inform(any())
            mother.giveMoney()
        }

        //验证 giveMoney() 一定在 inform() 之后执行
        verifyOrder {
            mother.inform(any())
            mother.giveMoney()
        }
    }

    /**
     * slot 用来抓取传入的参数
     */
    @Test
    fun `测试 slot 抓取传入的参数`(){
        val slot = slot<Int>()

        every { mother.inform(capture(slot)) } just runs
        kid.wantMoney()
        assertEquals(0,slot.captured)

    }
}

class Mother {

    fun giveMoney(): Int {
        return 100
    }

    fun inform(money: Int) {
        println("妈，我现在有 $money 元，我要向你要钱!")
    }
}

class Kid(
    private val mother: Mother
) {
    var money: Int = 0
        private set

    fun wantMoney() {
        mother.inform(money)
        this.money += mother.giveMoney()
    }

}