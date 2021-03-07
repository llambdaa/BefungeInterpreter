package interpreter.befunge.load
import java.io.File

import scala.io.Source

object Loader {

    def load( source: String ): Array[ Array[ Char ] ] = {

        val content = Source.fromFile( new File( source ) )
                            .getLines
                            .map( line => line.replaceAll( "\t", "    " ) )
                            .toList

        val width   = content.map( line => line.length ).max
        val height  = content.length
        val grid    = Array.ofDim[ Char ]( height, width )

        /**
         * Each line is dismembered into an array of characters,
         * which is then written into the respective grid line.
         */
        for ( ( array, index ) <- content.map( line => line.toCharArray ).zipWithIndex ) {

            Array.copy( array, 0, grid( index ), 0, array.size )

        }

        return grid

    }

}
