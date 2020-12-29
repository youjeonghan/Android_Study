package com.example.myapplication.Kotlin


// 과제
// Night, Monster (부모)
// SuperNight, SuperMonster (자식)
// 자식에 추가기능 넣어서 만들기


fun main(array: Array<String>) {
    val night = SuperNight(100, 10)
    val monster = SuperMonster(100, 15)     // SuperMonster도 Monster이다
    night.attack(monster)
    println()

    monster.attack(night)
}

// 상속이 만들어낸 특징
// - 자식 클래스는 부모 클래스의 타입이다
// - 부모 클래스는 자식 크래스의 타입이 아니다!


class SuperNight(hp: Int, power: Int) : Night(hp, power) {
    override fun attack(monster: Monster) {
        super.attack(monster)
        super.attack(monster)
        println("${javaClass.simpleName}가 공격을 마쳤습니다.")
    }

    override fun defense(damage: Int) {
        if (damage > 2) {
            super.defense(damage - 1)
        } else {
            super.defense(damage)
        }
    }
}

class SuperMonster(hp: Int, power: Int) : Monster(hp, power) {
    override fun attack(night: Night) {
            super.attack(night)
            super.attack(night)
        println("${javaClass.simpleName}가 공격을 마쳤습니다.")
        }

        override fun defense(damage: Int) {
            if (damage > 2) {
                super.defense(damage - 1)
            } else {
                super.defense(damage)
            }
        }
}