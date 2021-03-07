package interpreter.befunge.op;

import java.util.Arrays;

public enum Operation {

    PUSH( '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ),
    ADDITION( '+' ),
    SUBTRACTION( '-' ),
    MULTIPLICATION( '*' ),
    DIVISION( '/' ),
    MODULO( '%' ),
    NOT( '!' ),
    GREATER( '`' ),
    RIGHT( '>' ),
    LEFT( '<' ),
    UP( '^' ),
    DOWN( 'v' ),
    RANDOM( '?' ),
    HORIZONTAL_PIPE( '_' ),
    VERTICAL_PIPE( '|' ),
    STRING( '"' ),
    DUPLICATE( ':' ),
    SWAP( '\\' ),
    POP( '$' ),
    PRINT_VALUE( '.' ),
    PRINT_ASCII( ',' ),
    BRIDGE( '#' ),
    PUT_GRID( 'p' ),
    GET_GRID( 'g' ),
    READ_NUMBER( '&' ),
    READ_ASCII( '~' ),
    END( '@' ),
    NONE();

    //Arithmetic
    Operation( char ... symbols ) {

        this.symbols = symbols;

    }

    private char[] symbols;
    public char[] getSymbols() {

        return this.symbols;

    }

    /**
     * This function transforms a symbol (read from the grid)
     * into the matching operation.
     */
    public static Operation transform( char symbol ) {

        Operation[] set = Operation.values();
        for ( int i = 0; i < set.length; i++ ) {

            Operation target = set[ i ];
            if ( i > 0 ) {

                if ( symbol == target.getSymbols()[ 0 ] ) {

                    return target;

                }

            } else {

                /* For performance reasons, only the symbol set of PUSH is
                *  transformed into a list.
                *  For all other operation types, the symbol is checked directly. */
                if ( Arrays.asList( target.getSymbols() ).contains( symbol ) ) {

                    return Operation.PUSH;

                }

            }

        }

        return Operation.NONE;

    }

}
