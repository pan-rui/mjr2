package com.p2p.web.controller.comm;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class FileUploadController extends BaseController {

	private static Log log = LogFactory.getLog(FileUploadController.class);
	@RequestMapping(value = "/uploadFile")
	public String uploadFile(
			@RequestParam(value = "file", required = false) MultipartFile file) {
	
		System.out.println("开始");
		String path = request.getSession().getServletContext()
				.getRealPath("upload");
		DateFormat YYYYMM = new SimpleDateFormat("yyyyMM");
		DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
		DateFormat YYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date nowDate = new Date();
		path = path + File.separator+"images"+File.separator+YYYYMM.format(nowDate)+File.separator
				+YYYYMMDD.format(nowDate);
		String fileName = YYYYMMDDHHMMSSSSS.format(nowDate);
		// String fileName = new Date().getTime()+".jpg";
		System.out.println(path);
		File targetFile = new File(path,fileName);
		
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		return "result";
	}

	

}
