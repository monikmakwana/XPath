/*
Program of XPath Processor
By : Monik Makwana
*/
package Greycell_CodeTest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.Scanner;
import java.io.StringWriter;
import java.io.FileWriter;
import java.io.File;


public class TestCode 
{
    public static void main(String[] args) throws Exception 
    {
		Scanner sc=new Scanner(System.in);
		String expression,finalExpr;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("F:/Eclipse_Workspace/XPath_withFile/src/XMLfile.xml");
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.METHOD ,"xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");	
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		
		FileWriter writer = new FileWriter(new File("F:/Eclipse_Workspace/XPath_withFile/src/OutputXML.txt"));
		StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
		StreamResult result1 = new StreamResult(writer);
		
		XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
		
		System.out.println();
		System.out.print("expression : ");
		expression=sc.next();
		XPathExpression expr = xpath.compile(expression);
		
		Object resultt = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) resultt;	
		System.out.println();
		
		for (int i = 0; i < nodes.getLength(); i++)
		{
			DOMSource source = new DOMSource(nodes.item(i));
			transformer.transform(source, result);
			transformer.transform(source, result1);
		}   
		System.out.println(result.getWriter().toString());			
	}
}