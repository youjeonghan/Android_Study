package com.example.myapplication.Kotlin

fun main(array: Array<String>) {
    val night = Night(20, 4)
    val monster = Monster(15, 5)
    night.attack(monster)
    monster.attack(night)
}

open class Night(private var hp: Int, private var power: Int) {

    open fun attack(monster: Monster) {
        monster.defense(power)
    }

    open fun defense(damage: Int) {
        hp -= damage
        if (hp > 0) {
            heal()
            println("기사 현재 체력은 $hp 입니다")
        } else println("기사가 죽었습니다")
    }

    private fun heal() {
        // 아무때나 사용하는게 아니라
        // 공격 당하고 살아있을시에만 발동
        hp += 3
    }
}

open class Monster(private var hp: Int, private var power: Int) {
    open fun attack(night: Night) {
        night.defense(power)
    }

    open fun defense(damage: Int) {
        hp -= damage
        if (hp > 0) println("몬스터 현재 체력은 $hp 입니다")
        else println("몬스터가 죽었습니다")
    }
}