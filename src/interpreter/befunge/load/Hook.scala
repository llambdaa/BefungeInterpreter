package interpreter.befunge.load

import interpreter.befunge.exec.Executor

object Hook {

    def main(arguments: Array[String]): Unit = {
        if (arguments.length != 1) {
            println("Error: Invalid arguments.")
            println("Please issue with following arguments: <source path>")
        }

        Executor.run(arguments(0))
    }

}
