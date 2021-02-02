package com.njs.check.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class PdfUtil {

    public static ByteArrayOutputStream createPdfStream(String templateFilePath, String templateFileName, Map<String,String> resultMap){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {
            PdfReader reader = new PdfReader(templateFilePath + "\\" + templateFileName);
            PdfStamper stamp = new PdfStamper(reader, ba);

            //使用字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            /* 获取模版中的字段 */
            AcroFields form = stamp.getAcroFields();

            //填充表单
            if (resultMap != null) {
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    form.setFieldProperty(entry.getKey(), "textfont", bf, null);
                    form.setField(entry.getKey(), entry.getValue());
                }
            }

            stamp.setFormFlattening(true);//不能编辑
            stamp.close();

            reader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        }
        return ba;
    }
}
