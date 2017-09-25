package main.java.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import main.java.entity.File;


public interface FileService {
	File saveFile(MultipartFile mFile, File file) throws IllegalStateException, IOException;
	File getFile(Integer id);
	boolean checkExist(String path);
	List<File> getAllFiles();
}
