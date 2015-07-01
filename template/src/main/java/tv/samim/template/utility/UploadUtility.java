package tv.samim.template.utility;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author mojtaba khallash
 */
public class UploadUtility {

    public static File uploadFile(MultipartFile file, String filePath)
            throws IOException, FileNotFoundException {
        byte[] bytes = file.getBytes();
        File dir = new File(filePath);
        if (!dir.exists())
            dir.mkdirs();
        String fileName = file.getOriginalFilename().replace(" ", "_");
        String pathname = filePath + UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(fileName);
        File serverFile = new File(pathname);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return serverFile;
    }

    public static CommonsMultipartFile convertFileToMultipartFile(File file)
            throws IOException {
        DiskFileItem fileItem = new DiskFileItem("file", "text/plain", false,
                file.getName(), (int) file.length(), file.getParentFile());
        fileItem.getOutputStream();
        return new CommonsMultipartFile(fileItem);
    }
}
