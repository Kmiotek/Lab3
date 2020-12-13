package agh.cs.animalsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {
    @Test
    public void invalidParseTest(){
        String[] tab1 = new String[]{"f", "forward", "b", "backward", "r", "horse", "ForWard", "right", "l", "left"};
        MoveDirection[] tab2 = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(tab1));
    }

    @Test
    public void parseTest(){
        String[] tab1 = new String[]{"f", "forward", "b", "backward", "r", "right", "l", "left"};
        MoveDirection[] tab2 = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        Assertions.assertArrayEquals(tab2, OptionsParser.parse(tab1));
    }
}
