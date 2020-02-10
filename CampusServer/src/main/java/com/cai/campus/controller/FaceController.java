package com.cai.campus.controller;

import com.cai.campus.domain.app.ResponseData;
import com.cai.campus.service.IFaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaceController {
//    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
//    @ResponseBody
//    public HashMap uploadImg(@RequestParam(value = "file", required = false) MultipartFile file) {
//        return productServiceImp.uploadImg(file);
//    }

    private final IFaceService faceService;

    public FaceController(IFaceService faceService) {
        this.faceService = faceService;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseData test() {
        return faceService.testFunction();
    }

}
