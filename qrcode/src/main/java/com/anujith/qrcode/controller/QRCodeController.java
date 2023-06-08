package com.anujith.qrcode.controller;

import com.anujith.qrcode.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/qrcode")
@RequiredArgsConstructor
public class QRCodeController {

    private final QRCodeGeneratorService qrCodeGeneratorService;
    private static final String QR_CODE_IMAGE_PATH = "QRCode.png";
    @GetMapping()
    public String getQRCode(Model model) {
        String anujithGithub="https://github.com/anujith2008";
        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = qrCodeGeneratorService.getQRCodeImage(anujithGithub,250,250);

            // Generate and Save Qr Code Image in static/image folder
            //qrCodeGeneratorService.generateQRCodeImage(anujithGithub,250,250, QR_CODE_IMAGE_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("github",anujithGithub);
        model.addAttribute("qrcode",qrcode);

        return "qrcode";
    }

}
