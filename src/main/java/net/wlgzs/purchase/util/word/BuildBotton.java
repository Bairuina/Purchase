package net.wlgzs.purchase.util.word;

import com.spire.doc.Document;
import com.spire.doc.FieldType;
import com.spire.doc.HeaderFooter;
import com.spire.doc.Section;
import com.spire.doc.documents.BorderStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;

public class BuildBotton {

    public static final FieldType FIELD_PAGE = FieldType.Field_Page;

    public void setTableBotton(Document document){
        int allPage=document.getSections().getCount();
        for (int i = 0; i < allPage; i++) {
            Section section=document.getSections().get(i);
            HeaderFooter footer= section.getHeadersFooters().getFooter();
            Paragraph fpara= footer.addParagraph();
            fpara.appendField("页码", FIELD_PAGE);
            fpara.appendText("/");
            fpara.appendField("总页数",FieldType.Field_Num_Pages);
            fpara.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
//            fpara.getFormat().getBorders().getTop().setBorderType(BorderStyle.Single);
//            fpara.getFormat().getBorders().getTop().setLineWidth(1f);
//            fpara.getFormat().getBorders().getTop().setSpace(2f);
        }
    }
}
