package main.java.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import main.java.dao.FileDao;
import main.java.entity.File;



@Service
public class FileServiceImp implements FileService {
	
	@Autowired
	FileDao fileDao;
	
	@Override
	@Transactional
	public File saveFile(MultipartFile mFile, File file) throws IllegalStateException, IOException {
		System.out.println(file.getPath());
		File des = new File(file.getPath());
//		mFile.transferTo(des);
		return FileDao.save(file);
	}
	
	@Override
	@Transactional
	public File getFile(Integer id){
		return fileDao.getFile(id);
	}
	
	@Override
	@Transactional
	public boolean checkExist(String path){
		return fileDao.checkExist(path);
	}
	
	@Override
	public List<File> getAllFiles(){
		return fileDao.getAll();
	}
}
