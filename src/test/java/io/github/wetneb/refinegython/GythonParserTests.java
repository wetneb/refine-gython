package io.github.wetneb.refinegython;

import org.openrefine.expr.ParsingException;
import org.testng.annotations.Test;

public class GythonParserTests {
    
    GythonParser SUT = new GythonParser();

    @Test(expectedExceptions = ParsingException.class)
    public void testInvalidSource() throws ParsingException {
        SUT.parse("this is an invalid expression", "python3");
    }
    
    @Test
    public void testValidSource() throws ParsingException {
        SUT.parse("return value", "python3");
    }
}
