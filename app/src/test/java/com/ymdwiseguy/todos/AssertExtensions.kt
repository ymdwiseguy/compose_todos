package com.ymdwiseguy.todos

import kotlin.test.assertEquals

infix fun Any?.shouldBe(expected: Any?) {
    assertEquals(
        actual = this, expected = expected
    )
}