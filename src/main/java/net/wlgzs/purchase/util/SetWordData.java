package net.wlgzs.purchase.util;

import com.spire.doc.*;
import com.spire.doc.documents.TableRowHeightType;
import net.wlgzs.purchase.entity.OrderData;
import net.wlgzs.purchase.entity.ProductList;
import net.wlgzs.purchase.util.word.*;

import java.io.File;
import java.util.List;

public class SetWordData {
    public boolean makeWord(String ddbh, OrderData orderData) {
        Document doc = new Document(System.getProperty("user.dir") + "\\wordData" + "\\example.docx");
        //设置页眉
        //new BuildHeader().setTableHeader(doc);



        //设置页脚
        //new BuildBotton().setTableBotton(doc);

        Section sec = doc.getSections().get(0);
        Table tableOne = sec.getTables().get(1);
        //tableOne.autoFit(AutoFitBehaviorType.Auto_Fit_To_Contents);
        List<ProductList> productListData = orderData.getProductList();
        //添加行数
        for (int i = 0; i <productListData.size()-1; i++) {
            TableRow row = tableOne.getRows().get(1).deepClone();
            row.setHeightType(TableRowHeightType.Exactly);
            tableOne.getRows().insert(2,row);
        }
        //添加信息
        int number = 1;
        for (ProductList productList : productListData) {
            new SetTableOneCell().setData(tableOne.getRows().get(number).getCells(), "" + number, productList.getPpmc(), productList.getXhmc(), "" + productList.getSl());
            number++;
        }


        //设置显示ddbh的段落
        new SetSectionParagraph().setParagraph(sec.getParagraphs().get(2), ddbh);
        //设置表二的单元格信息
        Table tableTwo=sec.getTables().get(2);
        //tableTwo.autoFit(AutoFitBehaviorType.Auto_Fit_To_Contents);
        new SetTableTwoCell().setTableTwo(sec.getTables().get(2), orderData.getCgrmc(), orderData.getXflxrxm(), orderData.getXfdh(), orderData.getShdd());

        //保存文件
        doc.saveToFile(System.getProperty("user.dir") + "\\wordData\\" + ddbh + ".docx", FileFormat.Docx_2013);
        return true;
    }

    public boolean checkPdfPage(String ddbh){
        File file=new File(System.getProperty("user.dir")+"\\PDFData\\"+ddbh+".pdf");
        return file.exists();
    }
}
