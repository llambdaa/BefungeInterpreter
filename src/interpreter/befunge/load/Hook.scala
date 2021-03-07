package interpreter.befunge.load

import interpreter.befunge.exec.Executor

object Hook {

    def main( arguments: Array[ String ] ): Unit = {

        if ( arguments.length == 1 ) {

            Executor.run( arguments( 0 ) )

        } else {

            println( "Error: Invalid arguments.\nPlease issue with following arguments: <source path>" )

        }

    }

}
