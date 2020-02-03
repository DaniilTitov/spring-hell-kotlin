package ru.spring.hell.model


class Person {
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: Int? = null


    constructor() {}
    constructor(id: String?, firstName: String?, lastName: String?, age: Int?) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        return result
    }

    override fun toString(): String {
        return "Person(id=$id, firstName=$firstName, lastName=$lastName, age=$age)"
    }
}

