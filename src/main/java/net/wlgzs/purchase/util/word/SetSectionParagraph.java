package net.wlgzs.purchase.util.word;

import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextRange;

public class SetSectionParagraph {

    public void setParagraph(Paragraph paragraph, String ddbh) {
        TextRange range=paragraph.appendText(ddbh);
        new SetTableOneCell().setRange(range);
    }
}
