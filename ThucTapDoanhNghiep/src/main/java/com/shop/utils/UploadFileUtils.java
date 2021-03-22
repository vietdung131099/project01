package com.shop.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadFileUtils {

    public static void writeOfUpdate(byte[] bytes,String path) {
        // kiểm tra folder tồn tại chưa , nếu chưa tạo folder
        File file = new File("C:\\Users\\vietd\\Desktop\\ThucTapDoanhNghiep\\ThucTapDoanhNghiep\\src\\main\\webapp\\templateHome\\image");

        if(!file.exists()) { // nếu không tồn tại thì tạo
            file.mkdir(); // tạo folder
        }

        // ghi file
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(path))) {
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
