package cn.itcast.travel.controller;

import cn.itcast.travel.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author ply
 * @date 2021/4/8 - 16:43
 */
@Controller
public class ImgController {
    /**
     * 获取图片验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/imageCheckCode")
    public void imageCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        //产生4个随机验证码，12Ey
        String checkCode = ImageUtil.getCheckCode();
        //生成图片
        BufferedImage image = ImageUtil.createImage(80, 35,BufferedImage.TYPE_BYTE_GRAY,checkCode);
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECK_CODE", checkCode);

        ImageIO.write(image, "png", response.getOutputStream());
    }
}

