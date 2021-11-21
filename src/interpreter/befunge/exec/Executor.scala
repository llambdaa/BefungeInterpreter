package interpreter.befunge.exec

import java.util

import interpreter.befunge.load.Loader
import interpreter.befunge.op.Operation

import scala.util.Random

object Executor {

    def run(source: String): Unit = {
        val grid   = Loader.load(source)
        val width  = grid(0).length
        val height = grid.length
        //-------------------------------
        var pointerX, pointerY = 0
        val stack     = new util.Stack[Int]()
        var direction = Direction.RIGHT
        var mode      = Mode.EXECUTE
        var operation = Operation.transform(grid(pointerX)(pointerY))

        while (!operation.equals(Operation.END)) {

            //Reading the " operator lets the interpreter change into
            //read mode. Every character read until the operator is
            //detected again, will be interpreted as text and as such
            //gets added to the program stack.
            operation = Operation.transform(grid(pointerX)(pointerY))

            if (operation.equals(Operation.STRING)) {

                mode = if (mode.equals(Mode.EXECUTE)) Mode.READ else Mode.EXECUTE

            } else {

                if (mode.equals(Mode.READ)) {
                    stack.push(grid(pointerX)(pointerY).toInt)
                } else {
                    operation match {
                        case Operation.PUSH => {
                            stack.push(grid(pointerX)(pointerY).getNumericValue)
                        }
                        case Operation.ADDITION => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push(a + b)
                        }
                        case Operation.SUBTRACTION => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push(a - b)
                        }
                        case Operation.MULTIPLICATION => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push(a * b)
                        }
                        case Operation.DIVISION => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push( if ( a != 0 ) b / a else Int.MaxValue )
                        }
                        case Operation.MODULO => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push( if ( a != 0 ) b % a else Int.MaxValue )
                        }
                        case Operation.NOT => {
                            stack.push( if (stack.pop() != 0) 0 else 1 )
                        }
                        case Operation.GREATER => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push( if (b > a) 1 else 0 )
                        }
                        case Operation.RIGHT  => {
                            direction = Direction.RIGHT
                        }
                        case Operation.LEFT   => {
                            direction = Direction.LEFT
                        }
                        case Operation.UP     => {
                            direction = Direction.UP
                        }
                        case Operation.DOWN   => {
                            direction = Direction.DOWN
                        }
                        case Operation.RANDOM => {
                            val directions = Direction.values()
                            direction = directions(Random.nextInt(directions.length))
                        }
                        case Operation.HORIZONTAL_PIPE => {
                            direction = if (stack.pop() == 0)
                                            Direction.LEFT
                                        else Direction.RIGHT
                        }
                        case Operation.VERTICAL_PIPE   => {
                            direction = if (stack.pop() == 0)
                                            Direction.UP
                                        else Direction.DOWN
                        }
                        case Operation.DUPLICATE => {
                            stack.push(stack.peek())
                        }
                        case Operation.SWAP => {
                            val a = stack.pop()
                            val b = stack.pop()
                            stack.push(b)
                            stack.push(a)
                        }
                        case Operation.POP => {
                            stack.pop()
                        }
                        case Operation.PRINT_VALUE => {
                            print(stack.pop())
                        }
                        case Operation.PRINT_ASCII => {
                            print(stack.pop().toChar)
                        }
                        case Operation.PUT_GRID => {
                            val a = stack.pop() //y
                            val b = stack.pop() //x
                            val c = stack.pop() //v

                            if (a >= 1 && a <= grid.length &&
                                b >= 1 && b <= grid(0).length) {
                                grid(a - 1)(a - 1) = c.toChar
                            }
                        }
                        case Operation.GET_GRID => {
                            val a = stack.pop() //y
                            val b = stack.pop() //x
                            if (a >= 1 && a <= grid.length &&
                                b >= 1 && b <= grid(0).length) {
                                stack.push(grid(a)(b))
                            } else {
                                stack.push(0)
                            }
                        }
                        case Operation.READ_NUMBER => {
                            stack.push(scala.io.StdIn.readByte())
                        }
                        case Operation.READ_ASCII  => {
                            stack.push(scala.io.StdIn.readChar())
                        }
                        case Operation.BRIDGE =>
                        case Operation.NONE => {
                            direction match {
                                case Direction.RIGHT => {
                                    pointerX = (pointerX + 1) % width
                                }
                                case Direction.LEFT  => {
                                    if (pointerX > 0)
                                        pointerX -= 1
                                    else pointerX = width - 1
                                }
                                case Direction.DOWN  => {
                                    pointerY = (pointerY + 1) % height
                                }
                                case Direction.UP    => {
                                    if (pointerY > 0)
                                        pointerY -= 1
                                    else pointerY = height -1
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
