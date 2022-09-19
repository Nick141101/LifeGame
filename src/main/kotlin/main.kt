fun main() {

    println("Enter height and width of the board")
    val (height, width) = readln().split(" ").map { it.toInt() }

    println("Choose a rule ('Moore', 'Neumann', 'custom')")
    val rule = readln()

    val board = GameBoard(height, width, rule)

    println("Enter the number of living cells")
    val num = readln().toInt()

    println("Enter coordinates of living cells")
    for (i in 0 until num) {
        val (y, x) = readln().split(" ").map { it.toInt() }
        board.reviveCell(x, y)
    }

    println()
    println("Initial")
    board.printBoard()

    println("Enter the number of iterations (time)")
    val time = readln().toInt()
    println()

    for (i in 1..time) {
        println("#$i")
        board.nextStep()
        board.printBoard()
    }

}