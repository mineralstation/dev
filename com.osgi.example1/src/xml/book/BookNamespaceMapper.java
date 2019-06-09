package xml.book;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class BookNamespaceMapper extends NamespacePrefixMapper {

	public static BookNamespaceMapper INSTANCE = new BookNamespaceMapper();

	private static final String BOOK_PREFIX = "tns";
	private static final String BOOK_URI = "http://www.example.org/Book";

	private static final String SCHEMA_INSTANCE_PREFIX = "xsi";
	private static final String SCHEMA_INSTANCE_URI = "http://www.w3.org/2001/XMLSchema-instance";

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		if (BOOK_URI.equals(namespaceUri)) {
			return BOOK_PREFIX;
		} else if (SCHEMA_INSTANCE_URI.equals(namespaceUri)) {
			return SCHEMA_INSTANCE_PREFIX;
		}
		return suggestion;
	}

	@Override
	public String[] getPreDeclaredNamespaceUris() {
		return new String[] { BOOK_URI, SCHEMA_INSTANCE_URI };
	}

}

// private static final String FOO_PREFIX = ""; // DEFAULT NAMESPACE
// private static final String FOO_URI = "http://www.example.com/FOO";
// private static final String BAR_PREFIX = "bar";
// private static final String BAR_URI = "http://www.example.com/BAR";
// return new String[] { FOO_URI, BAR_URI };