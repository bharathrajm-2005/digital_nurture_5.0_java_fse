package design_patterns;

// Interface representing the Document product
interface Document {
    void open();
    void close();
}

// Concrete Document: WordDocument
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document (.docx)...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word Document (.docx)...");
    }
}

// Concrete Document: PdfDocument
class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document (.pdf)...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF Document (.pdf)...");
    }
}

// Concrete Document: ExcelDocument
class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document (.xlsx)...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel Document (.xlsx)...");
    }
}

// Abstract creator class declaring the Factory Method
abstract class DocumentFactory {
    // Factory method to be implemented by sub-classes
    public abstract Document createDocument();
}

// Concrete Creator for Word documents
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

// Concrete Creator for PDF documents
class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

// Concrete Creator for Excel documents
class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Test class to verify the Factory Method pattern implementation
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        // Instantiate different document factories
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Create documents using the factories
        Document doc1 = wordFactory.createDocument();
        Document doc2 = pdfFactory.createDocument();
        Document doc3 = excelFactory.createDocument();

        // Verify document operations
        doc1.open();
        doc1.close();

        System.out.println();

        doc2.open();
        doc2.close();

        System.out.println();

        doc3.open();
        doc3.close();
    }
}
