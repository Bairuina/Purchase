package net.wlgzs.purchase.util.word;

import com.spire.doc.Document;
import com.spire.doc.HeaderFooter;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.UnderlineStyle;
import com.spire.doc.fields.TextRange;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BuildHeader {
    public void setTableHeader(Document document){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar=Calendar.getInstance();
        String date=simpleDateFormat.format(calendar.getTime());
        int allPage=document.getSections().getCount();
        for (int i = 0; i < allPage; i++) {
            Section section=document.getSections().get(i);
            //获取页眉
            HeaderFooter header = section.getHeadersFooters().getHeader();
            Paragraph hpara= header.addParagraph();
            TextRange dateString = hpara.appendText(date);
            setTxtShow(dateString);
            hpara.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
        }
    }

    public void setTxtShow(TextRange textRange){
        textRange.getCharacterFormat().setUnderlineStyle(UnderlineStyle.None);
//        textRange.getCharacterFormat().setTextColor(Color.GRAY);
        textRange.getCharacterFormat().setFontName("微软雅黑");
        textRange.getCharacterFormat().setFontSize(11f);
//        textRange.getCharacterFormat().setBold(true);
    }
}
