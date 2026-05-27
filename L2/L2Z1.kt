package L2

data class UserInput(val name: String?, val email: String?, val age: String?)

data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {
    input ?: run {
        logs.add("Input is null")
        return null
    }

    val name = input.name?.trim() ?: run {
        logs.add("Name is null")
        return null
    }

    if (name.length < 3) {
        logs.add("Name too short")
        return null
    }

    val email = input.email?.trim()?.lowercase() ?: run {
        logs.add("Email is null")
        return null
    }

    if (!email.contains("@")) {
        logs.add("Invalid email")
        return null
    }

    val age = input.age?.let {
        it.toIntOrNull() ?: run {
            logs.add("Age is not a number")
            return null
        }
    } ?: run {
        logs.add("Age is null")
        return null
    }

    return UserProfile().apply {
        this.name = name
        this.email = email
        this.age = age
        this.isAdult = age >= 18
    }.also {
        logs.add("Profile created for $email")
    }
}

fun main() {
    val Input = UserInput(name = "Iza", email = "Iza@mail.com", age = "19")
    val Logs = mutableListOf<String>()
    val Profile = buildProfile(Input, Logs)
    println("P: $Profile")
    println("L: $Logs")
}