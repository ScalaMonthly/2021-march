package com.scalamonthly

import java.time.Instant
import java.util.UUID

object FundamentalsSpec extends weaver.SimpleIOSuite {

  import fundamentals._

  private def test[A, B](input: A, expected: B, f: A => B): weaver.Expectations = {
    expect(f(input) == expected)
  }

  pureTest("one") {
    val id = UUID.randomUUID()
    val p1 = Person(id, Name(FirstName("Jane"), LastName("Doe")))
    val input = BankAccount(id, AccountHolder(p1, None), 1000000L)
    val expectedOutput = p1.name.firstName
    test(input, expectedOutput, one)
  }

  pureTest("two") {
    val id = UUID.randomUUID()
    val p1 = Person(id, Name(FirstName("Jane"), LastName("Doe")))
    val p1u = Person(id, Name(FirstName("Mal"), LastName("Doe")))
    val input = BankAccount(id, AccountHolder(p1, None), 1000000L)
    val expectedOutput = BankAccount(id, AccountHolder(p1u, None), 1000000L)
    test(input, expectedOutput, two)
  }

  pureTest("three - secondary empty") {
    val id = UUID.randomUUID()
    val p1 = Person(id, Name(FirstName("Jane"), LastName("Doe")))
    val p1u = Person(id, Name(FirstName("Jane"), LastName("Fischer")))
    val input = BankAccount(id, AccountHolder(p1, None), 1000000L)
    val expectedOutput = BankAccount(id, AccountHolder(p1u, None), 1000000L)
    test(input, expectedOutput, three)
  }

  pureTest("three - secondary nonempty") {
    val id = UUID.randomUUID()
    val p1 = Person(id, Name(FirstName("Jane"), LastName("Doe")))
    val p2 = Person(id, Name(FirstName("John"), LastName("Doe")))
    val p1u = Person(id, Name(FirstName("Jane"), LastName("Fischer")))
    val p2u = Person(id, Name(FirstName("John"), LastName("Fischer")))
    val input = BankAccount(id, AccountHolder(p1, Some(p2)), 1000000L)
    val expectedOutput = BankAccount(id, AccountHolder(p1u, Some(p2u)), 1000000L)
    test(input, expectedOutput, three)
  }

  pureTest("four - Some") {
    val input = Animal.Dog(Animal.DogName("Spot"))
    val expectedOutput = Some(Animal.DogName("Spot"))
    test(input, expectedOutput, four)
  }

  pureTest("four - None") {
    val input = Animal.Fish(100)
    val expectedOutput = None
    test(input, expectedOutput, four)
  }

  pureTest("five") {
    val input = Animal.DogName("Spot")
    val expectedOutput = Animal.Dog(input)
    test(input, expectedOutput, five)
  }

  pureTest("six - Dog") {
    val id = UUID.randomUUID()
    val input = Household(id, HouseholdOccupants(Person(id, Name(FirstName("Sam"), LastName("Smith"))), Animal.Dog(Animal.DogName("Spot"))))
    val expectedOutput = Household(id, HouseholdOccupants(Person(id, Name(FirstName("Sam"), LastName("Smith"))), Animal.Dog(Animal.DogName("Spot II"))))
    test(input, expectedOutput, six)
  }

  pureTest("six - Turtle") {
    val id = UUID.randomUUID()
    val input = Household(id, HouseholdOccupants(Person(id, Name(FirstName("Sam"), LastName("Smith"))), Animal.Turtle(100)))
    val expectedOutput = Household(id, HouseholdOccupants(Person(id, Name(FirstName("Sam"), LastName("Smith"))), Animal.Turtle(100)))
    test(input, expectedOutput, six)
  }

  pureTest("seven") {
    import Tree._
    val input = Branch[Int](1, Branch[Int](2, Leaf(3), Branch[Int](4, Leaf(5), Leaf(6))), Leaf(7))
    val expectedOutput = Branch[Int](1, Branch[Int](2, Leaf(4), Branch[Int](4, Leaf(6), Leaf(7))), Leaf(8))
    test(input, expectedOutput, seven)
  }

}