package start.getreport;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.util.Hashtable;
import java.util.UUID;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;

/**
 * 使用方法：
 *
 * System.out.println(QRCodeUtil.getQRCode(request,"helloword"));
 *
 */
public class QRCodeUtil {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public QRCodeUtil() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static String getQRCode(HttpServletRequest request, String QRContent) throws Exception {
        int width = 75; // 二维码图片宽度
        int height = 75; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(QRContent,
                BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        String filename = UUID.randomUUID().toString();
        String path = request.getSession().getServletContext().getRealPath("");                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + File.separator +  "upload";
        File outputFile = new File(uploadPath + File.separator + filename+".jpg");
        writeToFile(bitMatrix, format, outputFile);
        return uploadPath + filename+".jpg";
    }
}