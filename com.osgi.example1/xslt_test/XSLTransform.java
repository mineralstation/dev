package xslt;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 
 * @see https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch24s10.html
 * @see https://stackoverflow.com/questions/1264849/pretty-printing-output-from-javax-xml-transform-transformer-with-only-standard-j
 * @see https://mvnrepository.com/artifact/net.sf.saxon/Saxon-HE/10.1
 */
public class XSLTransform {

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		long t1 = System.currentTimeMillis();
		String xslFile = "/Users/yayang/xslt/NormaliseMoodysTrialXML.xsl";
		String xmlFile = "/Users/yayang/xslt/transformXML-input.xml";

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
		StreamSource xmlsource = new StreamSource(xmlFile);
		StreamResult output = new StreamResult(System.out);

		// pretty print
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		transformer.transform(xmlsource, output);
		long t2 = System.currentTimeMillis();

		float timeTaken = (float) (t2 - t1) / (float) 1000;

		System.out.println();
		System.out.println("Time taken (seconds): " + timeTaken);
	}

}
