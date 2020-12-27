package com.example.myapplication.Kotlin

fun main(array: Array<String>) {
    // 1번 문제
    question1()

    // 2번 문제
    println(question2(80))

    // 3번 문제
    println(question3(82))

    // 4번 문제
    question4()
}

fun question1() {
    val list1 = mutableListOf<Int>()
    for (i in 0..9) {
        list1.add(i)
    }

    val list2 = mutableListOf<Boolean>()
    for (i in 0..9) {
        if (list1.get(i) % 2 == 0) list2.add(true)
        else list2.add(false)
    }
    println(list2)
}

fun question2(score: Int): String {
    if (score >= 80 && score <= 90) return "A"
    else if (score >= 70 && score <= 79) return "B"
    else if (score >= 60 && score <= 69) return "C"
    else return "F"
}

fun question3(num: Int): Int {
    return num % 10 + num / 10
}

fun question4() {
    for (i in 1 until 10){
        for (j in 1 until 10){
            println(""+ i + " * " + j +" = " + (i*j))
        }
        println()
    }
}