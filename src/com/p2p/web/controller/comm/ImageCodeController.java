package com.p2p.web.controller.comm;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.util.ImageCodeUtil;
import com.p2p.util.RandomUtils;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class ImageCodeController extends BaseController {
	private static Log log = LogFactory.getLog(ImageCodeController.class);
	@RequestMapping(value = "/randomImageCode")
	public String randomImageCode(String pageId) throws IOException {
		String key = pageId + "_code";
		// 将验证码存入页面KEY值的SESSION里面
		String randCode = RandomUtils.createFourRadom();
		session.setAttribute(key, randCode);
		// 图象生效
		BufferedImage image = ImageCodeUtil.createImageCode(randCode);
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", responseOutputStream);
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
		return null;
	}



}
