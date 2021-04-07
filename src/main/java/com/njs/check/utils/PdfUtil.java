package com.njs.check.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

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
            BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            /* 获取模版中的字段 */
            AcroFields form = stamp.getAcroFields();

            //填充表单
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    if(entry.getKey()=="img"){
                        String key = entry.getKey();
                        String imgPath = entry.getValue();
                        int pageNo = form.getFieldPositions(key).get(0).page;
                        Rectangle signRect = form.getFieldPositions(key).get(0).position;
                        float x = signRect.getLeft();
                        float y = signRect.getBottom();
                        Image image = Image.getInstance(imgPath);
                        PdfContentByte under = stamp.getOverContent(pageNo);
                        image.scaleToFit(signRect.getWidth(),signRect.getHeight());
                        image.setAbsolutePosition(x,y);
                        under.addImage(image);
                        continue;
                    }
                    form.setFieldProperty(entry.getKey(), "textfont", bf, null);
                    form.setField(entry.getKey(), entry.getValue());


            }

//            Map<String,String> imgmap = (Map<String,String>)resultMap.get("imgmap");
//            for(String key : imgmap.keySet()) {
//                String value = imgmap.get(key);
//                String imgpath = value;
//                int pageNo = form.getFieldPositions(key).get(0).page;
//                Rectangle signRect = form.getFieldPositions(key).get(0).position;
//                float x = signRect.getLeft();
//                float y = signRect.getBottom();
//                //根据路径读取图片
//                Image image = Image.getInstance(imgpath);
//                //获取图片页面
//                PdfContentByte under = stamp.getOverContent(pageNo);
//                //图片大小自适应
//                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
//                //添加图片
//                image.setAbsolutePosition(x, y);
//                under.addImage(image);
//            }

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
