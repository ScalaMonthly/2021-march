package com.scalamonthly

object challenge {

  sealed abstract class IdeaSize extends Product with Serializable
  object IdeaSize {
    case object Tiny extends IdeaSize
    case object Small extends IdeaSize
    case object Medium extends IdeaSize
    case object Large extends IdeaSize
  }
  sealed abstract class Totem extends Product with Serializable
  object Totem {
    case object SpinningTop extends Totem
    case object WeightedDie extends Totem
    case object ChessPiece extends Totem
    case object PokerChip extends Totem
  }
  final case class Author(id: String, firstName: String, lastName: Option[String], totem: Totem)
  final case class IdeaOrigin(timeCreated: java.time.Instant, author: Author)
  final case class Idea(id: String, size: IdeaSize, origin: IdeaOrigin, description: String)
  final case class Dream(id: String, idea: Option[Idea], childDreams: List[Dream])

  /**
    * Given a `Dream` instance representing all possible Dreams that could be encountered,
    * insert the given `idea` into the dream whose id is equal to `destinationDreamId`
    *
    * @param possibleDreams A representation of all possible dreams the target could have.
    * @param destinationDreamId The id of the dream at which to place the idea (perform inception)
    * @param idea The idea to perform inception with.
    * @return The updated representation of all Dreams, but with the idea placed into the appropriate dream.
    */
  def inception(possibleDreams: Dream, destinationDreamId: String, idea: Idea): Dream = ???

  /**
    * Given a representation of all possible dreams, the id of an author, and a totem,
    * update all occurrences of the author with the given id to have the new totem
    * rather than their existing one.
    *
    * @param dream Representation of all possible dreams
    * @param authorId Id of the author whose totem we are updating
    * @param totem The totem we are giving the author with the target id
    * @return The updated representation of Dreams containing the updates to the author's totem 
    */
  def updateTotem(possibleDreams: Dream, authorId: String, totem: Totem): Dream = ???

}