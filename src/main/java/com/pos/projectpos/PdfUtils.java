package com.pos.projectpos;


import com.lowagie.text.DocumentException;
import com.pos.projectpos.common.ProductDto;

import org.jsoup.Jsoup;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PdfUtils {
    
    String templateString = "C:\\Users\\iulia\\Downloads\\ProjectPos\\ProjectPos\\src\\main\\webapp\\WEB-INF\\pdfTemplates\\template.html";
    String modifiedString = "C:\\Users\\iulia\\Downloads\\ProjectPos\\ProjectPos\\src\\main\\webapp\\WEB-INF\\pdfTemplates\\modified.html";
    public PdfUtils(List<ProductDto> specItems, double price) {
        initialiseUserDataInPdf();
        initialiseDateInfo();
        addProduct(specItems, price);
        addTotalPrice(price);
        try {
            getPdf();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiseDateInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Adding date in the pdf....");
        Path input = Path.of(modifiedString);
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.parse(Files.readString(input), "UTF-8");
            doc.getElementById("dateInfo").append("Invoice #: #to be determined#><br /> Created: "+
                    dtf.format(now)+"<br />");
            Path output = Path.of(modifiedString);
            Files.writeString(output, doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiseUserDataInPdf() {
        Path input = Path.of(templateString);
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.parse(Files.readString(input), "UTF-8");
            doc.getElementById("userData").append(
                    "Username"+"<br />\n" +
                            "username@email.com");
            String modified = modifiedString;
            Path output = Path.of(modified);
            Files.writeString(output, doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void getPdf() throws IOException {
        String url = new File(modifiedString).toURI().toURL().toString();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();

        try (OutputStream os = Files.newOutputStream(Paths.get("C:\\Users\\iulia\\Downloads\\ProjectPos\\ProjectPos\\receipt.pdf"))) {
            renderer.createPDF(os);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    private void addProduct(List<ProductDto> shoppingCart, Double price) {
        System.out.println("Adding " + shoppingCart.size() + " in the pdf....");
        Path input = Path.of(modifiedString);
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.parse(Files.readString(input), "UTF-8");
            for (ProductDto s : shoppingCart
            ) {
                doc.getElementById("tableElem").append(" <tr class=\"item\">\n" +
                        "                <td class=\"titlu\">" + s.getName() + "</td>\n" +
                        "                <td>" + s.getCategory() + "</td>\n" +
                        "                <td>" + s.getPrice() + "</td>\n" +
                        "            </tr>");
            }

            Path output = Path.of(modifiedString);
            Files.writeString(output, doc.outerHtml());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTotalPrice(Double price) {
        System.out.println("Adding price in the pdf....");
        Path input = Path.of(modifiedString);
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.parse(Files.readString(input), "UTF-8");
            doc.getElementById("tableElem").append(" <tr class=\"total\">\n" +
                    "            <td></td>\n" +
                    "\n" +
                    "            <td>Total:" + price + "</td>\n" +
                    "        </tr>");
            Path output = Path.of(modifiedString);
            Files.writeString(output, doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

