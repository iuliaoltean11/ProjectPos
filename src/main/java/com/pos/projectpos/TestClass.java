package com.pos.projectpos;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestClass {

    public static void main(String[] args) {
//        File file = new File("src/main/resources/styles/pdfTemplates/template.html");
//        if(file.exists()){
//            System.out.println("merge la naiba cu el");
//        }
        Path input = Path.of("src/main/resources/styles/pdfTemplates/template.html");
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.parse(Files.readString(input), "UTF-8");
            doc.getElementById("userData").append(
                    "Username"+"<br />\n" +
                            "username@email.com");
            Path output = Path.of("src/main/resources/styles/pdfTemplates/modified.html");
            Files.writeString(output, doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
