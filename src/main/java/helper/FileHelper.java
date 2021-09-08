package helper;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    private File file;
    private final Logger log = LogManager.getLogger(FileHelper.class);

    public boolean remove(String filePath, String fileName) {
        try {
            file = new File(filePath + fileName);
            return file.exists() && file.delete();
        } catch (Exception ex) {
            log.error("Dosya silinirken hata oluştu! Hata mesajı: {}", ex.getMessage());
            return false;
        }
    }

    public boolean isExists(String filePath, String fileName) {
        file = new File(filePath + fileName);
        return file.exists();
    }

    public boolean isEmpty(String filePath, String fileName) {
        file = new File(filePath + fileName);
        return file.length() <= 0;
    }

    public void deleteDirectory(String directoryPath) {
        try {
            FileUtils.deleteDirectory(new File(directoryPath));
        } catch (IOException e) {
            log.error("Klasör silme esnasında sorun oluştu. Silinmek istenen şey klasör olmayabilir. Hata mesajı: {}", e.getMessage());
        }
    }

    public void createDirectory(String directoryPath) {
        file = new File(directoryPath);
        file.mkdir();
    }

    public boolean createNewFile(String filePath, String fileName) {
        try {
            file = new File(filePath + fileName);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void copyFile(String sourceFilePath, String destinationFilePath) {
        File srcFile = new File(sourceFilePath);
        File destFile = new File(destinationFilePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            log.error("Dosya kopyalama sırasında hata oluştu. Lütfen dosya yolunu ve ismini doğru yazdığınızdan emin olun! Hata mesajı: {}", e.getMessage());
        }
    }

    public void copyDirectory(String sourceFilePath, String destinationFilePath) {
        File srcFile = new File(sourceFilePath);
        File destFile = new File(destinationFilePath);
        try {
            FileUtils.copyDirectory(srcFile, destFile);
        } catch (IOException e) {
            log.error("Dosya kopyalama sırasında hata oluştu. Lütfen dosya yolunu ve ismini doğru yazdığınızdan emin olun! Hata mesajı: {}", e.getMessage());
        }
    }

    public String readFileAsString(String fileDirectory) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileDirectory)));
        } catch (IOException e) {
            log.error(e.getMessage());
            return "";
        }
    }
}
