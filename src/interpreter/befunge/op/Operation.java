package interpreter.befunge.op;

import java.util.Arrays;
import java.util.List;

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

    Operation(char ... symbols) {
        this.symbols = symbols;
    }

    private char[] symbols;

    public static Operation transform(char symbol) {
        return Arrays.stream(Operation.values())
                     .filter(op -> List.of(op.symbols)
                                       .contains(symbol))
                     .findFirst().orElse(Operation.NONE);
    }

}
