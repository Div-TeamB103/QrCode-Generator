package qr.az.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qr.az.dto.UserDto;
import qr.az.qrGenerators.Generator;

import java.awt.image.BufferedImage;
import java.nio.file.FileSystemNotFoundException;

@RestController
@RequestMapping("/api/v1/qr")
@CrossOrigin
public class QrController {


    @PostMapping("/qrcode")
    public ResponseEntity<byte[]> getQrCode(@RequestBody UserDto user , @RequestParam(name = "height") int height ,@RequestParam(name = "width") int width ) throws Exception {
        String details = "Ad: " + user.getName() + " \n" + "Soyad: " + user.getSurname() + "\n" + "Tel: " + user.getTelephone();
        System.out.println(details);

        byte[] qrCodeImage = Generator.generateByteQRCode(details, width, height);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeImage);
    }



}
