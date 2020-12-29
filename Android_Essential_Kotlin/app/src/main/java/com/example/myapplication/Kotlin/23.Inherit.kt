package com.example.myapplication.Kotlin

// 두번까지는 봐준다
// 두번 이상이 넘어가면 리펙토링 해라

// 25. 상속
// 부모로부터 설명서를 물려받는다!

fun main(args: Array<String>) {
    val superCar100 :SuperCar100 = SuperCar100()
    superCar100.drive()
    superCar100.stop()

    val bus100: Bus100 = Bus100()
    bus100.drive()
}

// class는 기본이 private 이다
// 부모 : Car100
// 자식 : Super100, Bus100
open class Car100() {
    fun drive() {

    }

    fun stop() {

    }

}

class SuperCar100() : Car100() {

}

class Bus100() : Car100() {

}