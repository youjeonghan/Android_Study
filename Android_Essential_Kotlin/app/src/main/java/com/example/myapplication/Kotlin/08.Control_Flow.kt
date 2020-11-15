package com.example.myapplication.Kotlin

// 08. 제어 흐름
// - if, else


fun main(args: Array<String>) {
    val a: Int = 5
    val b: Int = 10

    // if/else 사용 하는 방법 (1)
    println("1번")
    if (a > b) {
        println("a가 b 보다 크다")
    } else {
        println("a가 b 보다 작다")
    }
    println("3번")

    // if/else 사용 하는 방법 (2)
    if (a > b) {
        println("a가 b 보다 크다")
    }

    // if/else/elseif 사용 하는 방법 (3)
    if (a > b) {
        println("a가 b 보다 크다")
    } else if (a < b) {
        println("a가 b 보다 작다")
    } else if (a == b) {
        println("a가 b는 같다")
    }

    // 값을 리턴하는 if 사용방법 (1)
    val max = if (a > b) {
        a   // 5
    } else {
        b   // 10
    }

    // 값을 리턴하는 if 사용방법 (2)
    val max1 = if (a > b) a else b

    println()
    println(max)
}