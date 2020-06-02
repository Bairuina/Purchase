package net.wlgzs.purchase.util.word;

import com.spire.doc.TableCell;
import com.spire.doc.collections.CellCollection;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextRange;
import com.spire.doc.formatting.ParagraphFormat;

public class SetTableOneCell {

    public void setData(CellCollection cellCollection, String number, String ppmc, String xhbh, String sl) {
        //获取单元格
        TableCell cellOne = cellCollection.get(0);
        TableCell cellTwo = cellCollection.get(1);
        TableCell cellThree = cellCollection.get(2);
        TableCell cellfour = cellCollection.get(3);

        //去除单元格前面的回车符
        cellOne.getParagraphs().removeAt(0);
        cellTwo.getParagraphs().removeAt(0);
        cellThree.getParagraphs().removeAt(0);
        cellfour.getParagraphs().removeAt(0);

        //添加每个单元格信息
        TextRange rangeOne = cellOne.addParagraph().appendText(number);
        TextRange rangeTwo = cellTwo.addParagraph().appendText(ppmc);
        TextRange rangeThree = cellThree.addParagraph().appendText(xhbh);
        TextRange rangeFour = cellfour.addParagraph().appendText(sl);

        //设置字体样式
        setRange(rangeOne);
        setRange(rangeTwo);
        setRangeTwo(rangeThree,7f);
        setRange(rangeFour);
    }


    public void setRange(TextRange range) {
        //设置字体的样式的
        range.getCharacterFormat().setFontName("微软雅黑");
        //设置字体的大小
        range.getCharacterFormat().setFontSize(13f);
        //设置文字居中
        range.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
    }

    public void setRangeTwo(TextRange range,float sizeNumber) {
        //设置字体的样式的
        range.getCharacterFormat().setFontName("微软雅黑");
        //设置字体的大小
        range.getCharacterFormat().setFontSize(sizeNumber);
        //设置文字居中
        range.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
    }
}
