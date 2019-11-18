package net.wlgzs.purchase.util.word;

import com.spire.doc.Table;
import com.spire.doc.TableCell;
import com.spire.doc.TableRow;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.fields.TextRange;


public class SetTableTwoCell {
    public void setTableTwo(Table tableData, String cgrmc, String xflxrxm, String xfdh, String postion){
        //获取每一行
        TableRow tableRowOne=tableData.getRows().get(0);
        TableRow tableRowTwo=tableData.getRows().get(1);
        TableRow tableRowThree= tableData.getRows().get(2);

//        //设置每一行的样式
//        tableRowOne.setHeightType(TableRowHeightType.Exactly);
//        tableRowTwo.setHeightType(TableRowHeightType.Exactly);
//        tableRowThree.setHeightType(TableRowHeightType.Exactly);


        //获取单元格
        TableCell cellOne=tableRowOne.getCells().get(1);
        TableCell cellTwo=tableRowTwo.getCells().get(1);
        TableCell cellThree=tableRowTwo.getCells().get(3);
        TableCell cellFour=tableRowThree.getCells().get(1);

        //去除单元格前面的回车符
        cellOne.getParagraphs().removeAt(0);
        cellTwo.getParagraphs().removeAt(0);
        cellThree.getParagraphs().removeAt(0);
        cellFour.getParagraphs().removeAt(0);

        //添加每个单元格信息
        TextRange rangeOne=cellOne.addParagraph().appendText(cgrmc);
        TextRange rangeTwo=cellTwo.addParagraph().appendText(xflxrxm);
        TextRange rangeThree=cellThree.addParagraph().appendText(xfdh);
        TextRange rangeFour=cellFour.addParagraph().appendText(postion);

        //设置字体样式
        SetTableOneCell cellSet=new SetTableOneCell();
        cellSet.setRange(rangeOne);
        cellSet.setRange(rangeTwo);
        cellSet.setRange(rangeThree);
        cellSet.setRange(rangeFour);

    }
}
