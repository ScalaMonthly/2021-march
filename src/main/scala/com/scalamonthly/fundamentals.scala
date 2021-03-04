package com.scalamonthly

import java.util.UUID
import monocle.macros.GenLens
import monocle.std.option.some
import com.scalamonthly.fundamentals.Animal.DogName
import monocle.Prism
import com.scalamonthly.fundamentals.Animal.Dog
import monocle.function.Plated
import monocle.Traversal
import cats.implicits._

object fundamentals {

  final case class FirstName(value: String) extends AnyVal
  final case class LastName(value: String) extends AnyVal
  final case class Name(firstName: FirstName, lastName: LastName)
  final case class Person(id: UUID, name: Name)
  final case class AccountHolder(primary: Person, secondary: Option[Person])
  final case class BankAccount(id: UUID, holder: AccountHolder, balance: Long)

  /**
    * Return the firstName of the primary account holder.
    */
  def one(ba: BankAccount): FirstName = ???

  /**
    * Update the firstName of the primary account holder to be "Mal"
    */
  def two(ba: BankAccount): BankAccount = ???

  /**
    * Update the lastName of the primary and secondary (if exists) account holders to be "Fischer"
    */
  def three(ba: BankAccount): BankAccount = ???

  sealed abstract class Animal extends Product with Serializable
  object Animal {
    final case class DogName(value: String) extends AnyVal
    final case class Dog(name: DogName) extends Animal
    final case class Fish(length: Int) extends Animal
    final case class Turtle(age: Int) extends Animal
  }

  /**
    * Use a Prism to return the name of the animal if it is a dog, else return None.
    */
  def four(a: Animal): Option[DogName] = ???

  /**
    * Use a Prism to construct an Animal given a DogName.
    */
  def five(d: Animal.DogName): Animal = ???

  final case class HouseholdOccupants(owner: Person, pet: Animal)
  final case class Household(id: UUID, occupants: HouseholdOccupants)
  
  /**
    * If the household pet is a dog, append " II" to its name and return the entire household.
    */
  def six(h: Household): Household = ???

  sealed abstract class Tree[+A] extends Product with Serializable
  object Tree {
    final case class Branch[A](data: A, left: Tree[A], right: Tree[A]) extends Tree[A]
    final case class Leaf[A](data: A) extends Tree[A]
  }

  /**
    * Add 1 to every leaf node inside of the tree.
    * 
    * Hint: Look at the Plated type-class from Monocle.
    */
  def seven(tree: Tree[Int]): Tree[Int] = ???

}