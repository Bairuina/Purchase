package net.wlgzs.purchase.util.word;

import com.aspose.words.Document;
import org.junit.Test;


import java.io.File;

public class WordParsePDF {

    public  boolean WordParsePDF(String wordName) {
            File fileData = new File(System.getProperty("user.dir") + "\\WordData\\" +wordName+".docx");
            if (fileData.exists()) {
                try {
                    //doc路径
                    Document document = new Document(System.getProperty("user.dir") + "\\WordData\\" +wordName+".docx");
                    //pdf路径
                    File outputFile = new File(System.getProperty("user.dir") + "\\PDFData\\" +wordName+".pdf");
                    //操作文档保存
                    document.save(outputFile.getAbsolutePath(), com.aspose.words.SaveFormat.PDF);
                   return new File(System.getProperty("user.dir") + "\\WordData\\" +wordName+".docx").delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return  false;
    }

    @Test
    public void kasd(){
        WordParsePDF("55");
    }

}
