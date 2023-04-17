package solution

object Day7 extends Solution {

  import collection.mutable

  override val day: Int = 7
  override type Answer = Int

  override def solveFirstPart(lines: List[String]): Answer = {
    val fs = parse(lines)
    fs.rootDir.allDirsWithSize()
      .map(_._2)
      .filter(_ < 100000)
      .sum
  }

  override def solveSecondPart(lines: List[String]): Answer = {
    val fs = parse(lines)
    val rootSize = fs.rootDir.size
    fs.rootDir.allDirsWithSize()
      .map(_._2)
      .sorted
      .find(rootSize - _ < 40000000)
      .fold(-1)(identity)
  }


  def parse(lines: List[String]): FileSystem = {
    val root = Dir("/", mutable.Map.empty)

    val path = mutable.Stack.empty[Dir]
    var cur = root
    var i = 0

    while (i < lines.length) {
      val splitGroup = lines(i).split(" ")
      val cmd = splitGroup(1)
      cmd match {
        case "ls" =>
          i += 1
          while (i < lines.length && !lines(i).startsWith("$")) {
            val lsGroup = lines(i).split(" ")
            if (lsGroup(0) == "dir") {
              val dirName = lsGroup(1)
              cur.children(dirName) = Dir.emptyDir(dirName)
            } else {
              val Array(size, filename) = lsGroup
              cur.children(filename) = File(filename, size.toInt)
            }
            i += 1
          }

        case "cd" =>
          val target = splitGroup(2)
          target match {
            case "/" => cur = root
            case ".." =>
              if (path.nonEmpty) cur = path.pop()
            case _ =>
              if (!cur.children.contains(target)) {
                println("cd: no such directory")
              }
              cur.children(target) match {
                case d@Dir(_, _) =>
                  path.push(cur)
                  cur = d
                case File(_, _) => println("cannot cd to file")
              }
          }
          i += 1
        case _ =>
          println(s"err input ${lines(i)}")
          i += 1
      }
    }

    FileSystem(root)
  }


  case class FileSystem(rootDir: Dir)

  trait Node {
    def name: String

    def size: Int
  }

  case class Dir(name: String, children: mutable.Map[String, Node]) extends Node {
    override def size: Int = {
      if (children.isEmpty) 0
      else children.values.map(_.size).sum
    }

    def allDirsWithSize(): List[(Dir, Int)] = {
      // treeFold ?
      val builder = List.newBuilder[(Dir, Int)]
      val q = mutable.Queue.empty[Dir]
      q += this
      while (q.nonEmpty) {
        val cur = q.dequeue()
        builder += ((cur, cur.size))

        cur.children.values.foreach {
          case d@Dir(_, _) => q.enqueue(d)
          case _ => ()
        }
      }

      builder.result()
    }
  }


  object Dir {
    def emptyDir(name: String): Dir = Dir(name, mutable.Map.empty[String, Node])
  }

  case class File(name: String, size: Int) extends Node
}

