package com.example.myapplication.Kotlin

fun main(array: Array<String>) {
    // 1번 사칙연산을 수행할 수 있는 클래스
    val calculator1: Calculator1 = Calculator1()
    println(calculator1.add(3, 1))
    println(calculator1.sub(3, 1))
    println(calculator1.div(3, 2))
    println(calculator1.mul(3, 2))
    println()

    // -> Chaining(체이닝) 이용
    val calculator2: Calculator2 = Calculator2(10)
    calculator2.add(10).sub(5).div(5).mul(20)
    println()

    // 2번 은행 계좌 만들기
    val account: BankAccount = BankAccount("유정한", "1996", "7", "11")
    account.withdraw(10000)
    account.deposit(20000)
    account.deposit(5000)
    account.check()
    println()

    // 3번 TV 클래스
    val tv: TV = TV()
    tv.switch_channel()
    tv.switch_channel()
    tv.switch_power()
    tv.switch_channel()
    tv.switch_power()
    tv.switch_channel()
}

class Calculator1() {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun sub(a: Int, b: Int): Int {
        return a - b
    }

    fun div(a: Int, b: Int): Double {
        val result = a.toDouble() / b.toDouble()
        return result
    }

    fun mul(a: Int, b: Int): Int {
        return a * b
    }
}

class Calculator2(val init_num: Int) {
    fun add(a: Int): Calculator2 {
        val result = this.init_num + a
        println("현재 연산결과는 ${result}입니다.")
        return Calculator2(result)
    }

    fun sub(a: Int): Calculator2 {
        val result = this.init_num - a
        println("현재 연산결과는 ${result}입니다.")
        return Calculator2(result)
    }

    fun div(a: Int): Calculator2 {
        val result = this.init_num / a
        println("현재 연산결과는 ${result}입니다.")
        return Calculator2(result)
    }

    fun mul(a: Int): Calculator2 {
        val result = this.init_num * a
        println("현재 연산결과는 ${result}입니다.")
        return Calculator2(result)
    }
}

class BankAccount(var name: String, var birth_y: String, var birth_m: String, var birth_d: String) {
    var balance: Int

    init {
        balance = 0
    }

    fun withdraw(sum: Int) {
        if (sum <= 0) return println("0보다 큰 금액을 입금해야합니다.")

        balance = balance + sum
        println("입금후 잔액은 ${balance}원 입니다.")
    }

    fun deposit(sum: Int) {
        if (sum <= 0) return println("0보다 큰 금액을 출금해야합니다.")
        if (balance >= sum) {
            balance = balance - sum
            println("출금후 잔액은 ${balance}원 입니다.")
        } else {
            println("잔액이 모자릅니다.")
        }
    }

    fun check() {
        println("${name}님의 현재 잔액은 ${balance}원 입니다.")
    }
}

class TV() {
    val channel = mutableListOf<String>()
    var power: String
    var now_channel: Int = 0
//        set(value) {
//            // 값이 바뀔때마다 호출
//            field = value
//            if (field > 2){
//                field =0
//            }
//            if (field < 0){
//                field =2
//            }
//        }
//        get(){
//            // 호출될때마다
//            println("호출되었습니다")
//            return field
//        }


    init {
        power = "on"
        now_channel = 0
        channel.add("SBS")
        channel.add("MBC")
        channel.add("KBS")
        println("TV가 ${power} 상태입니다.\n\"${channel[now_channel]}\" 채널입니다.")
    }

    fun switch_power() {
        if (power == "off") power = "on"
        else power = "off"
        println("TV가 ${power} 상태입니다.")
    }

    fun switch_channel() {
        if (power == "off") {
            println("TV가 꺼져있습니다")
            return
        }
        if (now_channel + 1 == channel.size) {
            now_channel = 0
        } else {
            now_channel++
        }
        println("\"${channel[now_channel]}\"로 채널을 돌렸습니다")
        return
    }
}