# Loops & Ranges in Kotlin

## Table of Contents
1. [for Loop with Ranges](#for-loop-with-ranges)
2. [Range Types](#range-types)
3. [for with Collections](#for-with-collections)
4. [for with Index](#for-with-index)
5. [while Loop](#while-loop)
6. [do-while Loop](#do-while-loop)
7. [break and continue](#break-and-continue)
8. [Labeled break/continue](#labeled-breakcontinue)
9. [repeat() Function](#repeat-function)
10. [Progressions](#progressions)
11. [Practice Questions](#practice-questions)
12. [Interview Questions](#interview-questions)

---

## for Loop with Ranges

Kotlin's `for` loop iterates over anything that provides an iterator. The most common use is with ranges:

```kotlin
// Inclusive range: 1, 2, 3, 4, 5
for (i in 1..5) {
    print("$i ")
}

// Exclusive end: 1, 2, 3, 4 (excludes 5)
for (i in 1 until 5) {
    print("$i ")
}

// Descending: 5, 4, 3, 2, 1
for (i in 5 downTo 1) {
    print("$i ")
}

// With step: 1, 3, 5, 7, 9
for (i in 1..10 step 2) {
    print("$i ")
}

// Descending with step: 10, 7, 4, 1
for (i in 10 downTo 1 step 3) {
    print("$i ")
}
```

---

## Range Types

### IntRange
```kotlin
val intRange: IntRange = 1..100
println(50 in intRange)  // true
println(intRange.first)  // 1
println(intRange.last)   // 100
```

### LongRange
```kotlin
val longRange: LongRange = 1L..1_000_000_000L
```

### CharRange
```kotlin
val letters: CharRange = 'A'..'Z'
for (c in letters) print("$c ")  // A B C ... Z

val lowercase = 'a'..'z'
println('m' in lowercase)  // true
```

### Kotlin 1.8+ Open-ended Range
```kotlin
val openRange = 0..<10  // Same as 0 until 10
```

---

## for with Collections

```kotlin
// Iterate over list
val fruits = listOf("Apple", "Banana", "Cherry")
for (fruit in fruits) {
    println(fruit)
}

// Iterate over array
val numbers = intArrayOf(10, 20, 30, 40)
for (num in numbers) {
    print("$num ")
}

// Iterate over string characters
for (char in "Kotlin") {
    print("$char-")  // K-o-t-l-i-n-
}

// Iterate over map
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
for ((key, value) in map) {
    println("$key → $value")
}
```

---

## for with Index

### Using `withIndex()`
```kotlin
val colors = listOf("Red", "Green", "Blue")
for ((index, color) in colors.withIndex()) {
    println("$index: $color")
}
// 0: Red
// 1: Green
// 2: Blue
```

### Using `indices`
```kotlin
for (i in colors.indices) {
    println("colors[$i] = ${colors[i]}")
}
```

### Using `lastIndex`
```kotlin
for (i in 0..colors.lastIndex) {
    println("colors[$i] = ${colors[i]}")
}
```

### Using `forEachIndexed` (functional style)
```kotlin
colors.forEachIndexed { index, color ->
    println("$index: $color")
}
```

---

## while Loop

Executes as long as the condition is true (checks **before** each iteration):

```kotlin
var count = 5
while (count > 0) {
    print("$count ")
    count--
}
// Output: 5 4 3 2 1

// Reading until condition
var sum = 0
var n = 1
while (n <= 100) {
    sum += n
    n++
}
println("Sum 1-100: $sum")  // 5050
```

---

## do-while Loop

Executes at least once, then checks the condition (checks **after** each iteration):

```kotlin
var input: String
do {
    print("Enter 'quit' to exit: ")
    input = readLine() ?: "quit"
    println("You entered: $input")
} while (input != "quit")
```

```kotlin
// Guaranteed to execute at least once
var x = 0
do {
    println("x = $x")
    x++
} while (x < 0)  // Condition is false, but body ran once
// Output: x = 0
```

### while vs do-while

| Feature | while | do-while |
|---------|-------|----------|
| Check | Before iteration | After iteration |
| Minimum executions | 0 | 1 |
| Use case | May not need to execute | Must execute at least once |

---

## break and continue

### break — Exit the loop entirely
```kotlin
for (i in 1..10) {
    if (i == 5) break
    print("$i ")
}
// Output: 1 2 3 4
```

### continue — Skip the current iteration
```kotlin
for (i in 1..10) {
    if (i % 2 == 0) continue  // Skip even numbers
    print("$i ")
}
// Output: 1 3 5 7 9
```

---

## Labeled break/continue

Labels allow you to break or continue an **outer** loop from within a nested loop:

### Labeled break
```kotlin
outer@ for (i in 1..5) {
    for (j in 1..5) {
        if (i * j > 12) break@outer  // Exits BOTH loops
        print("${i * j}\t")
    }
    println()
}
```

### Labeled continue
```kotlin
outer@ for (i in 1..3) {
    for (j in 1..3) {
        if (j == 2) continue@outer  // Skips to next iteration of outer loop
        println("i=$i, j=$j")
    }
}
// Output:
// i=1, j=1
// i=2, j=1
// i=3, j=1
```

### Label syntax
- Define: `label@` before the loop
- Reference: `break@label` or `continue@label`
- Any valid identifier can be a label

---

## repeat() Function

A convenience function for repeating an action a specified number of times:

```kotlin
repeat(5) {
    print("* ")
}
// Output: * * * * *

// With index
repeat(3) { index ->
    println("Attempt ${index + 1}")
}
// Attempt 1
// Attempt 2
// Attempt 3
```

---

## Progressions

Ranges and `downTo`/`step` create **progressions** — sequences of numbers with a common difference:

```kotlin
// IntProgression properties
val progression = 1..10 step 2  // 1, 3, 5, 7, 9
println("First: ${progression.first}")   // 1
println("Last: ${progression.last}")     // 9
println("Step: ${progression.step}")     // 2

// Check if empty
val emptyProg = 10..1  // Empty progression (default step is 1, can't go from 10 to 1)
println("Is empty: ${emptyProg.isEmpty()}")  // true

// For reverse, use downTo
val reverse = 10 downTo 1 step 2  // 10, 8, 6, 4, 2
```

### Progression Rules
1. `a..b` → step 1, ascending (empty if a > b)
2. `a downTo b` → step -1, descending (empty if a < b)
3. `step` only accepts positive integers (direction determined by `..` or `downTo`)
4. Last element is adjusted to fit the step pattern

```kotlin
// Last element adjustment
println((1..10 step 3).toList())  // [1, 4, 7, 10]
println((1..11 step 3).toList())  // [1, 4, 7, 10]  — 11 is skipped
```

---

## Tips

1. **Prefer `for` with ranges** over while when iterating a known number of times
2. **Use `until` or `..<`** for zero-based indices: `for (i in 0 until list.size)`
3. **Use `withIndex()`** instead of manual index tracking
4. **Labels are rare** — only use for nested loops when logic requires it
5. **`repeat(n)`** is cleaner than `for (i in 0 until n)` when index isn't needed
6. **Avoid `while(true)`** unless it's an event loop — prefer structured iteration
7. **Destructuring** in for loops: `for ((key, value) in map)` is idiomatic Kotlin

---

## Practice Questions

### Q1. Write a for loop that prints numbers from 1 to 20, but only odd numbers.
<details><summary>Answer</summary>

```kotlin
for (i in 1..20 step 2) {
    print("$i ")
}
// OR
for (i in 1..20) {
    if (i % 2 != 0) print("$i ")
}
```
</details>

### Q2. Write a program to print a multiplication table for a given number using a for loop.
<details><summary>Answer</summary>

```kotlin
val num = 7
for (i in 1..10) {
    println("$num × $i = ${num * i}")
}
```
</details>

### Q3. What is the difference between `1..10` and `1 until 10`?
<details><summary>Answer</summary>

`1..10` includes 10 (range is 1, 2, ..., 10). `1 until 10` excludes 10 (range is 1, 2, ..., 9). Use `until` for array/list indices: `for (i in 0 until list.size)`.
</details>

### Q4. Write a while loop that finds the first power of 2 greater than 1000.
<details><summary>Answer</summary>

```kotlin
var power = 1
while (power <= 1000) {
    power *= 2
}
println("First power of 2 > 1000: $power")  // 1024
```
</details>

### Q5. Demonstrate the difference between while and do-while with a condition that's false initially.
<details><summary>Answer</summary>

```kotlin
var x = 10
while (x < 5) { println("while: $x"); x++ }   // Never executes

var y = 10
do { println("do-while: $y"); y++ } while (y < 5)  // Executes once: "do-while: 10"
```
</details>

### Q6. Write code to find the sum of digits of a number using a while loop.
<details><summary>Answer</summary>

```kotlin
var num = 12345
var sum = 0
while (num > 0) {
    sum += num % 10
    num /= 10
}
println("Sum of digits: $sum")  // 15
```
</details>

### Q7. Write a nested loop with labeled break to find a pair of numbers whose product exceeds 50.
<details><summary>Answer</summary>

```kotlin
outer@ for (i in 1..10) {
    for (j in 1..10) {
        if (i * j > 50) {
            println("First pair: $i × $j = ${i * j}")
            break@outer
        }
    }
}
```
</details>

### Q8. Use `repeat()` to print a simple pattern: `* ** *** ****`
<details><summary>Answer</summary>

```kotlin
repeat(4) { i ->
    repeat(i + 1) { print("*") }
    print(" ")
}
```
</details>

### Q9. Iterate over a map and print only entries where the value is greater than 50.
<details><summary>Answer</summary>

```kotlin
val scores = mapOf("Alice" to 85, "Bob" to 42, "Charlie" to 91, "Diana" to 67)
for ((name, score) in scores) {
    if (score > 50) println("$name: $score")
}
```
</details>

### Q10. What are progressions? What happens with `(1..10 step 3).toList()`?
<details><summary>Answer</summary>

Progressions are sequences of numbers with a common difference (step). `(1..10 step 3)` produces `[1, 4, 7, 10]`. The last element is included only if it fits the progression (10 = 1 + 3×3).
</details>

### Q11. Use `CharRange` to print the alphabet in reverse.
<details><summary>Answer</summary>

```kotlin
for (c in 'Z' downTo 'A') {
    print("$c ")
}
```
</details>

### Q12. Write a program using `continue` to skip multiples of 3 between 1 and 20.
<details><summary>Answer</summary>

```kotlin
for (i in 1..20) {
    if (i % 3 == 0) continue
    print("$i ")
}
// 1 2 4 5 7 8 10 11 13 14 16 17 19 20
```
</details>

### Q13. Write a loop to iterate a string and print each character's index and value.
<details><summary>Answer</summary>

```kotlin
for ((i, ch) in "Kotlin".withIndex()) {
    println("Index $i: '$ch'")
}
```
</details>

### Q14. Write a right-aligned number pyramid for height 5.
<details><summary>Answer</summary>

```kotlin
for (i in 1..5) {
    print(" ".repeat(5 - i))
    for (j in 1..i) print("$j ")
    println()
}
//     1
//    1 2
//   1 2 3
//  1 2 3 4
// 1 2 3 4 5
```
</details>

### Q15. What is the output of `for (i in 10..1) println(i)`?
<details><summary>Answer</summary>

Nothing is printed. `10..1` creates an empty IntRange because the start (10) is greater than the end (1) with the default step of 1. To count down, use `10 downTo 1`.
</details>

---

## Interview Questions

### Q1. Explain the difference between `..`, `until`, and `downTo`.
<details><summary>Answer</summary>

- `a..b` → inclusive range [a, b], ascending, step 1
- `a until b` or `a..<b` → exclusive end [a, b), ascending, step 1
- `a downTo b` → inclusive range [a, b], descending, step -1

All three create `IntProgression` objects. `step` can be chained to change the increment.
</details>

### Q2. How does Kotlin's for loop differ from Java's?
<details><summary>Answer</summary>

Kotlin has NO C-style `for(i=0; i<n; i++)` loop. Instead:
- `for (i in 0 until n)` — range-based iteration
- `for (item in collection)` — enhanced for-each
- `for ((i, v) in list.withIndex())` — indexed iteration
- `for ((k, v) in map)` — destructured map iteration

This is more readable and less error-prone.
</details>

### Q3. What are labeled breaks and when would you use them?
<details><summary>Answer</summary>

Labels (`label@`) allow `break` and `continue` to target a specific outer loop in nested structures. Use case: searching in a 2D grid where you want to exit both loops when found.
```kotlin
outer@ for (row in grid) {
    for (cell in row) {
        if (cell == target) break@outer
    }
}
```
Use sparingly — consider extracting to a function with `return` instead.
</details>

### Q4. What is a progression in Kotlin?
<details><summary>Answer</summary>

A progression is a sequence of values with a first element, last element, and step. Created by `..`, `downTo`, and `step` operators. Types: `IntProgression`, `LongProgression`, `CharProgression`. Properties: `first`, `last`, `step`. The for-loop is optimized by the compiler to a simple counting loop on JVM (no iterator allocation).
</details>

### Q5. How do you iterate backwards in Kotlin?
<details><summary>Answer</summary>

Use `downTo`:
```kotlin
for (i in 10 downTo 1) print("$i ")      // 10 9 8 ... 1
for (i in 10 downTo 1 step 2) print("$i ") // 10 8 6 4 2
for (c in 'z' downTo 'a') print("$c ")    // z y x ... a
```
`10..1` does NOT work (creates empty range).
</details>

### Q6. What is the `repeat()` function and when is it preferable to a for loop?
<details><summary>Answer</summary>

`repeat(n) { action }` executes the action n times. It's preferable when:
1. You don't need the loop variable (cleaner syntax)
2. You only need the zero-based index (`it` parameter)
3. Simpler readability for fixed-count repetitions

It's an inline function, so there's no function call overhead.
</details>

### Q7. Compare `for (i in 0 until list.size)` with `for (i in list.indices)`.
<details><summary>Answer</summary>

They produce the same result, but `list.indices` is more idiomatic Kotlin. It returns an `IntRange` from 0 to `list.lastIndex`. Benefits:
- More readable and self-documenting
- Can't accidentally use wrong bound
- Works with any indexed collection
Both are equally efficient — no extra objects created.
</details>

### Q8. How does Kotlin optimize for loops over ranges at the JVM level?
<details><summary>Answer</summary>

For simple ranges (`1..n`, `0 until n`), the Kotlin compiler generates efficient bytecode equivalent to a Java counting loop:
```java
// Kotlin: for (i in 1..n)
// JVM bytecode equivalent:
int i = 1;
while (i <= n) { body; i++; }
```
No `IntRange` or `Iterator` objects are allocated. The `step` optimization generates `i += step` instead of `i++`.
</details>

### Q9. What is destructuring in for loops?
<details><summary>Answer</summary>

Kotlin allows destructuring declarations in for loops:
```kotlin
for ((key, value) in map) { }     // Map entries
for ((index, elem) in list.withIndex()) { }  // IndexedValue
for ((name, age) in listOfPairs) { }  // Pair/data class

// Uses component1(), component2() etc. under the hood
```
Works with any class that has `componentN()` functions (data classes, Map.Entry, Pair, etc.).
</details>

### Q10. What happens if step is 0 or negative in a range progression?
<details><summary>Answer</summary>

`step` must be **positive** and at least 1. `step 0` throws `IllegalArgumentException: Step must be positive`. The direction is determined by `..` (ascending) or `downTo` (descending), not by the step value. So `10 downTo 1 step 3` goes 10, 7, 4, 1 (step is positive but direction is downward).
</details>

### Q11. How do you iterate a collection in reverse without creating a new list?
<details><summary>Answer</summary>

```kotlin
val list = listOf(1, 2, 3, 4, 5)

// Option 1: indices in reverse
for (i in list.lastIndex downTo 0) println(list[i])

// Option 2: reversed() — creates a reversed view (lazy for lists)
for (item in list.asReversed()) println(item)

// Option 3: reversed() creates a new list
for (item in list.reversed()) println(item)
```
`asReversed()` is the most efficient as it creates a view without copying.
</details>

### Q12. Explain the difference between `forEach` and a `for` loop.
<details><summary>Answer</summary>

- `for` loop: language construct, supports `break`/`continue`, generates efficient bytecode
- `forEach`: inline function, no `break`/`continue` (use `return@forEach` to skip), slightly more overhead

Use `for` for general iteration. Use `forEach` in functional chains or when you don't need `break`/`continue`.
```kotlin
list.forEach { item -> println(item) }
// return@forEach acts like continue, not break
```
</details>

### Q13. Can you use `break` inside `forEach`? How do you work around it?
<details><summary>Answer</summary>

Direct `break` doesn't work in `forEach`. Workarounds:
1. Use a `for` loop instead
2. Use `run` block with labeled return:
```kotlin
run breaking@ {
    list.forEach {
        if (it == 3) return@breaking  // Exits the run block
        println(it)
    }
}
```
3. Use `first()`, `take()`, or `takeWhile()` for early termination
</details>

### Q14. What is the difference between `list.indices` and `list.withIndex()`?
<details><summary>Answer</summary>

- `list.indices` returns `IntRange` (0..lastIndex). Only provides indices, you access elements manually with `list[i]`.
- `list.withIndex()` returns `Iterable<IndexedValue<T>>`. Provides both index and element via destructuring: `for ((i, v) in list.withIndex())`.

Use `indices` when you only need position. Use `withIndex()` when you need both index and value.
</details>

### Q15. How would you implement an infinite loop in Kotlin and why might you use one?
<details><summary>Answer</summary>

```kotlin
while (true) {
    // Server event loop, game loop, etc.
    if (shouldStop) break
}
```
Use cases: server main loops, game loops, input processing loops, state machines. Always include a `break` condition or `return`. Kotlin doesn't have a dedicated syntax for infinite loops like some languages — `while (true)` is the standard idiom.
</details>
