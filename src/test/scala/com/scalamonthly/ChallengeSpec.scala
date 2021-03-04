package com.scalamonthly

object ChallengeSpec extends weaver.SimpleIOSuite {

  import challenge._

  pureTest("perform inception by placing idea into dream with target id") {
    val input = Dream("one", None, List(
      Dream("two", None, List.empty),
      Dream("three", None, List(
        Dream("four", None, List.empty)
      ))
    ))

    val idea = Idea("idea1", IdeaSize.Tiny, IdeaOrigin(java.time.Instant.now, Author("a1", "Cobb", None, Totem.SpinningTop)), "Really sneaky idea")

    val expectedOutput = Dream("one", None, List(
      Dream("two", None, List.empty),
      Dream("three", None, List(
        Dream("four", Some(idea), List.empty)
      ))
    ))
    val actualOutput = inception(input, "four", idea)
    expect(actualOutput == expectedOutput)
  }

  pureTest("update totem for all occurrences of given author") {
    val idea1 = Idea("idea1", IdeaSize.Tiny, IdeaOrigin(java.time.Instant.now, Author("a1", "Cobb", None, Totem.SpinningTop)), "Really sneaky idea")
    val idea2 = Idea("idea2", IdeaSize.Small, IdeaOrigin(java.time.Instant.now, Author("a2", "Arthur", None, Totem.WeightedDie)), "Really sneaky idea 2")
    val idea2Updated = Idea("idea2", IdeaSize.Small, IdeaOrigin(java.time.Instant.now, Author("a2", "Arthur", None, Totem.PokerChip)), "Really sneaky idea 2")

    val input = Dream("one", None, List(
      Dream("two", None, List.empty),
      Dream("three", Some(idea2), List(
        Dream("four", Some(idea1), List(
          Dream("five", Some(idea2), List.empty)
        ))
      ))
    ))

    val expectedOutput = Dream("one", None, List(
      Dream("two", None, List.empty),
      Dream("three", Some(idea2Updated), List(
        Dream("four", Some(idea1), List(
          Dream("five", Some(idea2Updated), List.empty)
        ))
      ))
    ))
    val actualOutput = updateTotem(input, "a2", Totem.PokerChip)
    expect(actualOutput == expectedOutput)
  }

}