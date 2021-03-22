package com.shop.controller.api;


import com.shop.controller.BaseController;
import com.shop.model.request.image.UploadFile;
import com.shop.utils.UploadFileUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/api/image")
public class UploadImageApi extends BaseController {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UploadFile uploadFile = toClass(req,UploadFile.class);
        byte[] decodeBase64 = Base64.getDecoder().decode(uploadFile.getBase64().getBytes());
        UploadFileUtils.writeOfUpdate(decodeBase64,"C:\\Users\\vietd\\Desktop\\ThucTapDoanhNghiep\\ThucTapDoanhNghiep\\src\\main\\webapp\\templateHome\\image\\"+uploadFile.getName());
    }
}
