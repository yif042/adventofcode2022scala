package solution

import munit.FunSuite
import solution.Day7._

import scala.collection.mutable

class Day7Suite extends FunSuite with BaseTest {
  override def input: String =
    """$ cd /
      |$ ls
      |dir a
      |14848514 b.txt
      |8504156 c.dat
      |dir d
      |$ cd a
      |$ ls
      |dir e
      |29116 f
      |2557 g
      |62596 h.lst
      |$ cd e
      |$ ls
      |584 i
      |$ cd ..
      |$ cd ..
      |$ cd d
      |$ ls
      |4060174 j
      |8033020 d.log
      |5626152 d.ext
      |7214296 k""".stripMargin

  test("first part answer") {
    assertEquals(
      solveFirstPart(lines),
      95437
    )
  }

  test("second part answer") {
    assertEquals(
      solveSecondPart(lines),
      24933642
    )
  }

  test("parse file system") {
    assertEquals(
      parse(lines),
      FileSystem(
        Dir("/",
          List(
            Dir("a",
              List(
                Dir("e",
                  List(File("i", 584)).map(f => (f.name, f)).to(mutable.Map)
                ),
                File("f", 29116),
                File("g", 2557),
                File("h.lst", 62596)
              ).map(f => (f.name, f)).to(mutable.Map)
            ),
            File("b.txt", 14848514),
            File("c.dat", 8504156),
            Dir("d",
              List(
                File("j", 4060174),
                File("d.log", 8033020),
                File("d.ext", 5626152),
                File("k", 7214296),
              ).map(f => (f.name, f)).to(mutable.Map)
            )
          ).map(f => (f.name, f)).to(mutable.Map)
        )
      )
    )
  }

}
