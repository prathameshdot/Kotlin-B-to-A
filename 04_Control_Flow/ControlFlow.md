# Control Flow in Kotlin — if/when Expressions

## Table of Contents
1. [if Expression](#if-expression)
2. [if-else](#if-else)
3. [if-else-if Chain](#if-else-if-chain)
4. [if as Expression](#if-as-expression)
5. [when Expression](#when-expression)
6. [when with Argument](#when-with-argument)
7. [when without Argument](#when-without-argument)
8. [when with Type Checks](#when-with-type-checks)
9. [when with Ranges](#when-with-ranges)
10. [Exhaustive when](#exhaustive-when)
11. [Practice Questions](#practice-questions)
12. [Interview Questions](#interview-questions)

---

## if Expression

In Kotlin, `if` is an **expression** — it returns a value. This is one of the key differences from Java where `if` is a statement.

### Basic if
```kotlin
val age = 18
if (age >= 18) {
    println("Adult")
}
```

---

## if-else

```kotlin
val temperature = 30
if (temperature > 25) {
    println("It's hot!")
} else {
    println("It's cool!")
}
```

---

## if-else-if Chain

```kotlin
val score = 85
if (score >= 90) {
    println("Grade: A")
} else if (score >= 80) {
    println("Grade: B")
} else if (score >= 70) {
    println("Grade: C")
} else if (score >= 60) {
    println("Grade: D")
} else {
    println("Grade: F")
}
```

---

## if as Expression (Returns a Value)

This is a major Kotlin feature — `if` can return a value, replacing Java's ternary operator:

```kotlin
// Kotlin has NO ternary operator (?:) — use if expression instead
val a = 10
val b = 20
val max = if (a > b) a else b
println("Max: $max")  // 20

// With blocks — last expression is the return value
val result = if (a > b) {
    println("a is greater")
    a  // returned
} else {
    println("b is greater")
    b  // returned
}

// Assigning from if-else-if
val grade = if (score >= 90) "A"
            else if (score >= 80) "B"
            else if (score >= 70) "C"
            else "F"
```

> **Important:** When using `if` as an expression, the `else` branch is **required** (the compiler needs a value for all paths).

---

## when Expression

`when` is Kotlin's powerful replacement for Java's `switch`. It's far more flexible and concise.

### Basic Syntax
```kotlin
when (value) {
    match1 -> action1
    match2 -> action2
    else -> defaultAction
}
```

---

## when with Argument

### Simple Value Matching
```kotlin
val day = 3
when (day) {
    1 -> println("Monday")
    2 -> println("Tuesday")
    3 -> println("Wednesday")
    4 -> println("Thursday")
    5 -> println("Friday")
    6 -> println("Saturday")
    7 -> println("Sunday")
    else -> println("Invalid day")
}
```

### Multiple Values on One Branch
```kotlin
when (day) {
    1, 2, 3, 4, 5 -> println("Weekday")
    6, 7 -> println("Weekend")
    else -> println("Invalid")
}
```

### when as Expression
```kotlin
val dayName = when (day) {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    4 -> "Thursday"
    5 -> "Friday"
    6 -> "Saturday"
    7 -> "Sunday"
    else -> "Invalid"
}
println(dayName)
```

### Blocks in Branches
```kotlin
val result = when (day) {
    1 -> {
        val msg = "Start of the week"
        println(msg)
        "Monday"  // Last expression is the return value
    }
    5 -> {
        println("Almost weekend!")
        "Friday"
    }
    else -> "Other day"
}
```

---

## when without Argument

Used as a replacement for `if-else-if` chains — each branch has its own condition:

```kotlin
val temperature = 35

when {
    temperature < 0 -> println("Freezing!")
    temperature < 15 -> println("Cold")
    temperature < 25 -> println("Comfortable")
    temperature < 35 -> println("Warm")
    else -> println("Hot!")
}
```

```kotlin
val name = "Kotlin"
val length = name.length

when {
    name.startsWith("K") -> println("Starts with K")
    length > 10 -> println("Long name")
    name.contains("lin") -> println("Contains 'lin'")
    else -> println("Other")
}
// Prints: "Starts with K" (first matching branch wins)
```

---

## when with Type Checks (`is`)

```kotlin
fun describe(obj: Any): String = when (obj) {
    is Int -> "Integer: $obj (doubled: ${obj * 2})"  // Smart cast!
    is String -> "String of length ${obj.length}"     // Smart cast!
    is Boolean -> if (obj) "True" else "False"        // Smart cast!
    is List<*> -> "List of size ${obj.size}"          // Smart cast!
    is Double -> "Double: ${"%.2f".format(obj)}"
    else -> "Unknown: ${obj::class.simpleName}"
}

println(describe(42))          // Integer: 42 (doubled: 84)
println(describe("Hello"))    // String of length 5
println(describe(true))       // True
println(describe(listOf(1)))  // List of size 1
```

---

## when with Ranges and `in`

```kotlin
val score = 85
val grade = when (score) {
    in 90..100 -> "A"
    in 80 until 90 -> "B"
    in 70 until 80 -> "C"
    in 60 until 70 -> "D"
    in 0 until 60 -> "F"
    else -> "Invalid score"
}
println("Score $score → Grade $grade")
```

```kotlin
val char = 'K'
when (char) {
    in 'A'..'Z' -> println("Uppercase letter")
    in 'a'..'z' -> println("Lowercase letter")
    in '0'..'9' -> println("Digit")
    else -> println("Special character")
}
```

---

## Exhaustive when

When `when` is used as an expression, the compiler ensures all possible values are covered. This is especially useful with `enum` and `sealed` classes:

### With Enum
```kotlin
enum class Direction { NORTH, SOUTH, EAST, WEST }

val dir = Direction.NORTH
val message = when (dir) {  // Must be exhaustive when used as expression
    Direction.NORTH -> "Going up"
    Direction.SOUTH -> "Going down"
    Direction.EAST -> "Going right"
    Direction.WEST -> "Going left"
    // No else needed — all enum values covered!
}
```

### With Sealed Class
```kotlin
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
    data object Loading : Result()
}

fun handleResult(result: Result): String = when (result) {
    is Result.Success -> "Data: ${result.data}"
    is Result.Error -> "Error: ${result.message}"
    is Result.Loading -> "Loading..."
    // No else needed — sealed class is exhaustive!
}
```

> **Tip:** Starting from Kotlin 1.7, you can use `when` with sealed classes as a statement and get a warning if not exhaustive. Use `.let {}` or assign to `val` to force exhaustive checking.

---

## when vs switch Comparison

| Feature | Kotlin `when` | Java `switch` |
|---------|--------------|---------------|
| Fall-through | No (each branch is independent) | Yes (needs `break`) |
| Expression | Yes (returns value) | Statement (expression in Java 14+) |
| Types | Any type | int, char, String, enum |
| Range check | `in 1..10` | Not supported |
| Type check | `is String` | `instanceof` (Java 17+ only) |
| No argument | `when { }` | Not supported |
| Multiple values | `1, 2, 3 ->` | `case 1: case 2: case 3:` |
| Pattern matching | Built-in | Preview (Java 21+) |

---

## Tips

1. **Prefer `when` over `if-else-if`** chains — more readable and powerful
2. **Use `when` as expression** to assign values — forces you to handle all cases
3. **No ternary operator** in Kotlin — use `if (cond) a else b`
4. **Branch order matters** — first matching branch wins
5. **Sealed + when** = powerful pattern matching with compile-time exhaustiveness
6. **when without argument** replaces complex if-else-if logic cleanly
7. **Smart casts** in `when`/`is` branches eliminate explicit casting

---

## Practice Questions

### Q1. Write a Kotlin if expression that returns the absolute value of a number.
<details><summary>Answer</summary>

```kotlin
val n = -42
val abs = if (n >= 0) n else -n
println(abs)  // 42
```
</details>

### Q2. Convert this Java ternary to Kotlin: `String s = x > 0 ? "positive" : "non-positive";`
<details><summary>Answer</summary>

```kotlin
val s = if (x > 0) "positive" else "non-positive"
```
Kotlin has no ternary operator — use `if` expression.
</details>

### Q3. Write a when expression that maps month numbers (1-12) to season names.
<details><summary>Answer</summary>

```kotlin
val month = 7
val season = when (month) {
    12, 1, 2 -> "Winter"
    3, 4, 5 -> "Spring"
    6, 7, 8 -> "Summer"
    9, 10, 11 -> "Autumn"
    else -> "Invalid month"
}
```
</details>

### Q4. Write a when without argument that categorizes a temperature value.
<details><summary>Answer</summary>

```kotlin
val temp = 22
when {
    temp < 0 -> println("Freezing")
    temp < 15 -> println("Cold")
    temp < 25 -> println("Pleasant")
    temp < 35 -> println("Warm")
    else -> println("Hot")
}
```
</details>

### Q5. Why does Kotlin require an `else` branch when using if/when as an expression?
<details><summary>Answer</summary>

When used as an expression, `if`/`when` must produce a value for every possible input. Without an `else` branch, there would be paths that don't produce a value, which is a compile error. The compiler needs a guaranteed return value.
</details>

### Q6. Write a when expression using `in` to convert a percentage score to a letter grade.
<details><summary>Answer</summary>

```kotlin
val score = 92
val grade = when (score) {
    in 90..100 -> "A"
    in 80..89 -> "B"
    in 70..79 -> "C"
    in 60..69 -> "D"
    in 0..59 -> "F"
    else -> "Invalid"
}
```
</details>

### Q7. Write a function that uses when with `is` to describe the type and value of an Any parameter.
<details><summary>Answer</summary>

```kotlin
fun describe(value: Any): String = when (value) {
    is Int -> "Integer: $value"
    is String -> "String: \"$value\" (length ${value.length})"
    is Double -> "Double: $value"
    is Boolean -> "Boolean: $value"
    else -> "Other: $value"
}
```
</details>

### Q8. Can a when branch have multiple conditions? Show an example.
<details><summary>Answer</summary>

Yes, separate values with commas:
```kotlin
val char = 'A'
when (char) {
    'a', 'e', 'i', 'o', 'u',
    'A', 'E', 'I', 'O', 'U' -> println("Vowel")
    else -> println("Consonant or other")
}
```
</details>

### Q9. Write nested if expressions to determine the largest of three numbers.
<details><summary>Answer</summary>

```kotlin
val a = 10; val b = 25; val c = 15
val max = if (a >= b && a >= c) a
          else if (b >= a && b >= c) b
          else c
println("Max: $max")  // 25
```
</details>

### Q10. What is the difference between when as a statement vs when as an expression?
<details><summary>Answer</summary>

As a **statement**: the result is not used, `else` is optional, branches execute side effects.
As an **expression**: assigned to a variable or returned, `else` is required (unless exhaustive with enum/sealed), the last expression in each branch is the returned value.
</details>

### Q11. Write a when expression with block branches that log before returning.
<details><summary>Answer</summary>

```kotlin
val input = 2
val result = when (input) {
    1 -> {
        println("Processing one")
        "One"
    }
    2 -> {
        println("Processing two")
        "Two"
    }
    else -> {
        println("Processing other")
        "Other"
    }
}
println("Result: $result")
```
</details>

### Q12. How does when handle the order of branches? What if multiple branches match?
<details><summary>Answer</summary>

Branches are evaluated top-to-bottom, and the **first matching branch** is executed. Only one branch ever runs (no fall-through). Order matters when conditions overlap:
```kotlin
when {
    x > 0 -> println("Positive")     // Matches first
    x > 10 -> println("Big positive") // Never reached if x is 5
}
```
</details>

### Q13. Write a when that checks if a character is a vowel, consonant, digit, or special character.
<details><summary>Answer</summary>

```kotlin
val ch = 'e'
when {
    ch in "aeiouAEIOU" -> println("Vowel")
    ch in 'a'..'z' || ch in 'A'..'Z' -> println("Consonant")
    ch in '0'..'9' -> println("Digit")
    else -> println("Special character")
}
```
</details>

### Q14. How do you make a when expression exhaustive without else?
<details><summary>Answer</summary>

Use `enum class` or `sealed class`/`sealed interface` — the compiler knows all possible values:
```kotlin
enum class Color { RED, GREEN, BLUE }
val msg = when (Color.RED) {  // No else needed
    Color.RED -> "Red"
    Color.GREEN -> "Green"
    Color.BLUE -> "Blue"
}
```
</details>

### Q15. Write an if expression to return "even" or "odd" for a given number.
<details><summary>Answer</summary>

```kotlin
val n = 7
val parity = if (n % 2 == 0) "even" else "odd"
println("$n is $parity")  // 7 is odd
```
</details>

---

## Interview Questions

### Q1. How is Kotlin's `if` different from Java's `if`?
<details><summary>Answer</summary>

In Kotlin, `if` is an **expression** that returns a value, while in Java it's a statement. This means you can write `val max = if (a > b) a else b` directly. Kotlin doesn't need Java's ternary operator `?:` because `if` itself serves that purpose. When used as an expression, the `else` branch is mandatory.
</details>

### Q2. Why doesn't Kotlin have a ternary operator?
<details><summary>Answer</summary>

Because `if` is already an expression that returns a value: `val x = if (cond) a else b`. This is more readable than `cond ? a : b` and consistent with the language design. The `?:` symbol is reserved for the Elvis operator in Kotlin.
</details>

### Q3. Compare Kotlin's `when` with Java's `switch`.
<details><summary>Answer</summary>

Key differences:
1. `when` has no fall-through (no need for `break`)
2. `when` can be an expression (returns value)
3. `when` supports any type (not just int/char/String/enum)
4. `when` supports ranges (`in 1..10`), type checks (`is`), and arbitrary conditions
5. `when` without argument replaces if-else-if chains
6. `when` with sealed classes/enums enforces exhaustiveness
</details>

### Q4. What is exhaustive when and why is it important?
<details><summary>Answer</summary>

When `when` is used as an expression with `enum` or `sealed` class/interface, the compiler ensures all possible types/values are covered. This is important because:
1. Adding a new subclass/enum value causes a compile error, forcing you to handle it
2. Eliminates bugs from unhandled cases
3. Makes code more maintainable
</details>

### Q5. Can you use when without an argument? When would you?
<details><summary>Answer</summary>

Yes. `when` without an argument acts as a cleaner `if-else-if` chain where each branch has its own boolean condition:
```kotlin
when {
    x.isBlank() -> handleEmpty()
    x.length > 100 -> handleLong(x)
    x.startsWith("http") -> handleUrl(x)
    else -> handleDefault(x)
}
```
Use it when conditions aren't simple value matching but complex boolean expressions.
</details>

### Q6. How does smart casting work in when branches?
<details><summary>Answer</summary>

When using `is` checks in `when` branches, the compiler smart-casts the variable within that branch:
```kotlin
when (obj) {
    is String -> println(obj.length)  // obj is cast to String
    is List<*> -> println(obj.size)   // obj is cast to List
}
```
No explicit cast is needed. The compiler guarantees the type within each branch.
</details>

### Q7. What happens if no branch matches in a when statement vs expression?
<details><summary>Answer</summary>

- **Statement**: Nothing happens, execution continues after the `when` block. `else` is optional.
- **Expression**: Compile error if `else` is missing (unless exhaustive with enum/sealed). The compiler requires a value for all possible inputs.
</details>

### Q8. Can when branches have side effects?
<details><summary>Answer</summary>

Yes. Each branch can contain a block of code `{ }`. When used as an expression, the last expression in the block is the returned value:
```kotlin
val result = when (x) {
    1 -> {
        log("Got 1")
        performAction()
        "one"  // returned
    }
    else -> "other"
}
```
</details>

### Q9. How would you handle a nullable value in a when expression?
<details><summary>Answer</summary>

```kotlin
val value: String? = null
when (value) {
    null -> println("Value is null")
    "" -> println("Value is empty")
    else -> println("Value: $value")
}
// You can check for null explicitly as a branch condition
```
</details>

### Q10. Explain the relationship between sealed classes and when.
<details><summary>Answer</summary>

Sealed classes restrict inheritance to a known set of subclasses (defined in the same file/module). When combined with `when`, the compiler knows all possible types and can enforce exhaustiveness without `else`. This provides:
1. Compile-time safety when patterns change
2. IDE warnings for unhandled cases
3. A pattern similar to algebraic data types in functional languages
</details>

### Q11. Can you use when to replace a map/dictionary lookup?
<details><summary>Answer</summary>

Yes, for a fixed set of mappings:
```kotlin
fun getStatusMessage(code: Int): String = when (code) {
    200 -> "OK"
    404 -> "Not Found"
    500 -> "Internal Server Error"
    else -> "Unknown: $code"
}
```
For larger or dynamic mappings, an actual `Map` is more appropriate.
</details>

### Q12. How do you check if a value is in a range within when?
<details><summary>Answer</summary>

Use the `in` keyword:
```kotlin
when (age) {
    in 0..12 -> "Child"
    in 13..17 -> "Teenager"
    in 18..64 -> "Adult"
    in 65..120 -> "Senior"
    else -> "Invalid age"
}
```
Also supports `!in` for negation: `!in 0..100 -> "Out of range"`.
</details>

### Q13. What is the advantage of when over if-else-if for type dispatching?
<details><summary>Answer</summary>

`when` with `is` provides cleaner syntax, automatic smart casting, and better readability:
```kotlin
// when — clean and concise
when (shape) {
    is Circle -> println(shape.radius)  // Smart cast
    is Rectangle -> println(shape.width * shape.height)
}

// if-else — verbose
if (shape is Circle) println((shape as Circle).radius)
else if (shape is Rectangle) println((shape as Rectangle).width * ...)
```
</details>

### Q14. Can you capture the when subject in a variable?
<details><summary>Answer</summary>

Yes, since Kotlin 1.4 you can bind the subject to a `val`:
```kotlin
when (val response = executeRequest()) {
    is Success -> println(response.data)
    is Error -> println(response.message)
}
// `response` is scoped to the when block
```
This avoids creating a separate variable before the when.
</details>

### Q15. How do you force a when statement to be exhaustive?
<details><summary>Answer</summary>

1. With `enum`/`sealed class` — automatically exhaustive when used as expression
2. For statements, assign to a dummy variable: `val _ = when (x) { ... }`
3. Or call `.let {}`: `when (x) { ... }.let {}`
4. Since Kotlin 1.7, the compiler shows warnings for non-exhaustive when on sealed types even as statements
</details>
