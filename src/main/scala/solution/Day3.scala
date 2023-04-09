package solution

object Day3 extends Solution {
  override val day: Int = 3
  override type Answer = Int

  override def solveFirstPart(lines: List[String]): Answer =
    lines
      .map(s => Rucksack(s).compartmentType())
      .map(t => t.priority())
      .sum

  override def solveSecondPart(lines: List[String]): Answer =
    lines
      .grouped(3)
      .map { xs =>
        val List(s1, s2, s3) = xs
        ThreeGroup.make(s1, s2, s3)
      }
      .map(tg => tg.badgeType())
      .map(t => t.priority())
      .sum

  case class Rucksack(content: String) extends AnyVal {
    def compartmentType(): ItemType = {
      val (left, right) = content.splitAt(content.length / 2)
      ItemType((left.toSet & right.toSet).head)
    }
  }

  case class ItemType(char: Char) extends AnyVal {
    def priority(): Int =
      if (char.isUpper) char - 'A' + 27
      else char - 'a' + 1
  }

  case class ThreeGroup(r1: Rucksack, r2: Rucksack, r3: Rucksack) {
    def badgeType(): ItemType = {
      val typeChar = (r1.content.toSet &
        r2.content.toSet &
        r3.content.toSet).head

      ItemType(typeChar)
    }
  }

  private object ThreeGroup {
    def make(s1: String, s2: String, s3: String): ThreeGroup =
      new ThreeGroup(Rucksack(s1), Rucksack(s2), Rucksack(s3))
  }
}