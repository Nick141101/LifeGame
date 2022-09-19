class GameBoard(
    val height: Int,
    val width: Int,
    val rule: String
) {

    private var board = Array(height) { Array(width) {0} }

    fun reviveCell(x: Int, y: Int) {
        board[y][x] = 1
    }
    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (board[i][j] == 0) print(". ") else print("\u001B[32m" + "X " + "\u001B[0m")
            }
            println()
        }
        println()
    }

    fun nextStep() {
        val newBoard = Array(height) { y -> Array(width) { x -> board[y][x] } }
        when (rule) {
            "Moore" -> {
                for (i in 0 until height) {
                    for (j in 0 until width) {
                        var neighborsCount = 0
                        val neighborsIndices = listOf(i - 1 to j - 1, i - 1 to j, i - 1 to j + 1, i to j - 1,
                                                     i to j + 1, i + 1 to j - 1, i + 1 to j, i + 1 to j + 1)
                        for ((y, x) in neighborsIndices)
                            if (x in 0 until width && y in 0 until height)
                                neighborsCount += board[y][x]

                        when (neighborsCount) {
                            3 -> newBoard[i][j] = 1
                            0, 1, 4, 5, 6, 7, 8 -> newBoard[i][j] = 0
                        }
                    }
                }
            }

            "Neumann" -> {
                for (i in 0..height) {
                    for (j in 0..width) {
                        var neighborsCount = 0
                        val neighborsIndices = listOf(i - 1 to j, i to j - 1, i to j + 1, i + 1 to j)
                        for ((y, x) in neighborsIndices)
                            if (x in 0 until width && y in 0 until height)
                                neighborsCount += board[y][x]

                        when (neighborsCount) {
                            3 -> newBoard[i][j] = 1
                            0, 1, 4 -> newBoard[i][j] = 0
                        }
                    }
                }

            }

            "custom" -> {
                for (i in 0..height) {
                    for (j in 0..width) {
                        var neighborsCount = 0
                        val neighborsIndices = listOf(i - 1 to j - 1, i - 1 to j + 1, i + 1 to j - 1, i + 1 to j + 1)
                        for ((y, x) in neighborsIndices)
                            if (x in 0 until width && y in 0 until height)
                                neighborsCount += board[y][x]

                        when (neighborsCount) {
                            3 -> newBoard[i][j] = 1
                            0, 1, 4 -> newBoard[i][j] = 0
                        }
                    }
                }
            }
        }
        board = newBoard
    }

}