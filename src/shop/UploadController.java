package shop;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


/**
 * Created by Administrator on 2017/3/28.
 */
@WebServlet(name = "UploadController")
public class UploadController extends HttpServlet {
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private static final String UPLOAD_DIRECTORY = "upload";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = new Product();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_FILE_SIZE);
        String uploadPath = request.getServletContext().getRealPath("./")+File.separator
                +UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdir();
        }
        try {

            List<FileItem> items = upload.parseRequest(request);
            List<String> forms=new ArrayList<>();


            for(FileItem item:items)
            {
                if(item.isFormField())
                {
                   forms.add(item.getString());
                }else
                    {
                        BASE64Encoder encoder = new BASE64Encoder();

                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath+File.separator+ encoder.encode(fileName.getBytes());
                        System.out.print("uploadPath:"+uploadPath+"\nfilename："+fileName);
                        File storeFile = new File(filePath);
                        try {
                            item.write(storeFile);
                          filePath=  filePath.replaceAll("\\\\","\\\\\\\\");
                           filePath=filePath.replace("\\.","");
                            product.setImgpath(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
            }

//            product.setName(new String(forms.get(0).getBytes("ISO-8859-1"),"utf-8"));
//            product.setCity(new String(forms.get(1).getBytes("ISO-8859-1"),"utf-8"));
           product.setName(new String(forms.get(0).getBytes("ISO-8859-1"),"UTF-8"));
            product.setCity(new String(forms.get(1).getBytes("ISO-8859-1"),"UTF-8"));
            product.setPrice(Float.parseFloat(forms.get(2)));
            product.setNumber(Integer.parseInt(forms.get(3)));


        } catch (FileUploadException e) {
            e.printStackTrace();
        }



//        String name = request.getParameter("name");
//        String city = request.getParameter("city");
//        int price =Integer.parseInt(request.getParameter("price"));
//        int number =Integer.parseInt(request.getParameter("number"));
//        product.setName(name);
//        product.setCity(city);
//        product.setPrice(price);
//        product.setNumber(number);
        product.save();

        response.sendRedirect("/");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
