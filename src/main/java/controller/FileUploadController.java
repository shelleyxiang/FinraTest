package main.java.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import main.java.entity.File;
import main.java.service.FileService;



@RestController
public class FileUploadController {
	@Autowired
	FileService fs;
	
	private final String dir = "files";
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	public File uploadFile(@RequestParam("file") MultipartFile inputfile) throws IOException{
		String path = (new File(dir)).getPath() + "/" + inputfile.getOriginalFilename();
		if(fs.checkExist(path)){
			return null;
		}else{
			File f = new File(inputfile.getOriginalFilename(), new Date(), inputfile.getSize(), path);
			fs.saveFile(inputfile, f);
			return f;
		}
	}
	
	
	@RequestMapping(value = "/viewfile/{id}", method = RequestMethod.GET)
	public File getFileInfo(@PathVariable Integer id) throws FileUploadException {
        if (fs.getFile(id) == null) throw new FileUploadException("File doesn't exist");
        else return fs.getFile(id);
    }
	
	@RequestMapping(value = "/viewfiles", method = RequestMethod.GET)
	public List<File> getAllFiles(){
		return fs.getAllFiles();
	}
	
	
	@ExceptionHandler(value = FileUploadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String showError(FileUploadException e){
		return "File upload error: " + e.getMessage();
	}
	
	//Handle the exception and return bad request
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String showError2(FileUploadException e){
		return "Invalid number: " + e.getMessage();
	}
}
