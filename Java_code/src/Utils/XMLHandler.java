package Utils;

import org.json.XML;
import parser.XmlJsonParser;
import parser.ParserException;

public class XMLHandler
{
    private static XmlJsonParser parser = new XmlJsonParser();

    public static void writeToXml(String filename, Object object)
        throws ParserException
    {
        parser.toXml(object, filename);
    }
}
