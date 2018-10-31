package vykmnsk;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class EventFilter {

    final static String EVENTS_INPUT_DIR = "events";

    public static void main (String[] args) throws IOException {
        printHeader();
        try (Stream<Path> paths = Files.walk(Paths.get(EVENTS_INPUT_DIR))) {
            paths.filter(Files::isRegularFile)
                .map(path -> path.toFile())
                .map(file -> parseXml(file))
                .map(doc -> readEvent(doc))
                .filter(event -> event.isValid())
                .forEach(System.out::println);
        }
    }

    private static void printHeader() {
        System.out.println("buyer_party,seller_party,premium_amount,premium_currency");
    }

    static Event readEvent(Document doc){
        Event event = new Event();
        event.setBuyerParty(readField(doc, "//buyerPartyReference/@href"));
        event.setSellerParty(readField(doc, "//sellerPartyReference/@href"));
        event.setPremiumAmount(readField(doc, "//paymentAmount/amount"));
        event.setPremiumCurrency(readField(doc, "//paymentAmount/currency"));
        return event;
    }

    static Document parseXml(File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (Exception e){
            System.out.println(String.format("Could not parse file=%s error=%s", file, e));
        }
        return doc;
    }

    static String readField(Document doc, String xpath) {
        String field = null;
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpathObj = xPathfactory.newXPath();
        XPathExpression expr;
        try {
            expr = xpathObj.compile(xpath);
            field = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (Exception e) {
            //ignore and use default value
        }
        return field;
    }

}
