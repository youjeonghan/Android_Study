package com.example.myapplication.Kotlin


// 21. 접근 제어자

fun main(array: Array<String>) {

    val testAccess: TestAccess = TestAccess("아무개")
    // Private 키워드 때문에 외부에서 더 이상 사용할 수 없다
//    testAccess.test()
//    println(testAccess.name)
//    testAccess.name = "아무개 투"
//    println(testAccess.name)

    val reward: Reward = Reward()
    reward.rewardAmount = 2000

    val runnigCar: RunningCar = RunningCar()
}

class Reward() {
    var rewardAmount: Int = 1000
}

class TestAccess {
    // 클래스 외부에서 접근을 허용하지 않음
    private var name: String = "홍길동"

    constructor(name: String) {
        this.name = name
    }


    private fun test() {
        println("테스트")
    }
}

class RunningCar() {
    fun runFast() {
        run()
    }

    private fun run() {

    }
}