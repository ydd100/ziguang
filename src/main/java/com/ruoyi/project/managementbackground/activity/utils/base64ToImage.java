package com.ruoyi.project.managementbackground.activity.utils;

import com.ruoyi.framework.config.RuoYiConfig;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
@Component
public class base64ToImage {

    public static String test(String codetype,String base64) throws IOException {
        byte[] bs = new byte[1024];
        bs = Base64.getMimeDecoder().decode(base64);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;

//        Date date = new Date();
//        String strDateFormat = "yyyyMMddHHmmss";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
         Long nowtime = System.currentTimeMillis();

        String codepath = "";
        if(codetype.equals("1")){
            codepath = RuoYiConfig.getProfile()+"/unlimit-code/dept_"+ nowtime +".png";
        }else if(codetype.equals("2")){
            codepath = RuoYiConfig.getProfile()+"/unlimit-code/rank_"+ nowtime +".png";
        }else if(codetype.equals("3")){
            codepath = RuoYiConfig.getProfile()+"/unlimit-code/onperson_"+ nowtime +".png";
        }else if(codetype.equals("4")){
            codepath = RuoYiConfig.getProfile()+"/unlimit-code/all_"+ nowtime +".png";
        }
        file = new File(codepath);
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String temp = "";
        if(codetype.equals("1")){
            temp = "/profile/unlimit-code/dept_"+ nowtime +".png";
        }else if(codetype.equals("2")){
            temp = "/profile/unlimit-code/rank_"+ nowtime +".png";
        }else if(codetype.equals("3")){
            temp = "/profile/unlimit-code/onperson_"+ nowtime +".png";
        }else if(codetype.equals("4")){
            temp = "/profile/unlimit-code/all_"+ nowtime +".png";
        }
        return temp;
    }
}
