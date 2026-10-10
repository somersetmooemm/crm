package utils;

import com.codeborne.pdftest.PDF;

import java.io.File;
import java.io.IOException;

public class PDFUtils {

    public static String getText(File file) throws IOException {
        return new PDF(file).text;
    }

    public static int getCountPage(File file) throws IOException {
        return new PDF(file).numberOfPages;
    }
}