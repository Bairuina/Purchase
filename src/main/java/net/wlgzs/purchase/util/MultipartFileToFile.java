package net.wlgzs.purchase.util;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @@author 王耀兴
 * @createTime 2019-10-12 15:17
 *
 */
public class MultipartFileToFile {

    /**
     * MultipartFile转File
     * @param file MultipartFile流
     * @throws Exception
     */
    public static void multipartFileToFile(@RequestParam MultipartFile file)throws Exception{
        File tofile =null;
        if (file.equals("")||file.getSize()<=0){
            file=null;
        }else {
            InputStream ins=null;
            ins =file.getInputStream();
            tofile=new File(file.getOriginalFilename());
            inputStreamToFile(ins,tofile);
            ins.close();
        }
    }

    public static void inputStreamToFile(InputStream ins,File file){
        try{
            OutputStream os=new FileOutputStream(file);
            int byteRead=0;
            byte[] buffer=new byte[8192];
            while((byteRead=ins.read(buffer,0,8192))!=-1){
                os.write(buffer,0,byteRead);
            }
            os.close();
            ins.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
