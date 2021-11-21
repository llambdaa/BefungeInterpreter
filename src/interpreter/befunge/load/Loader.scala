package interpreter.befunge.load

import java.io.File
import scala.io.Source

object Loader {

    def load(source: String): Array[Array[Char]] = {
        val content = Source.fromFile(new File(source))
                            .getLines
                            .map(line => line.replaceAll( "\t", "    "))
                            .toList

        val width  = content.map(line => line.length).max
        val height = content.length
        val grid   = Array.ofDim[Char](height, width)

        for ((array, index) <- content.map(line => line.toCharArray).zipWithIndex) {
            Array.copy(array, 0, grid(index), 0, array.size)
        }

        grid
    }

}
