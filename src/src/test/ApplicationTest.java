package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import main.java.controller.FileUploadController;
import main.java.dao.FileDao;
import main.java.entity.File;
import main.java.service.FileService;
import main.java.service.FileServiceImp;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Mock
	FileDao fileDao;
	@InjectMocks
	FileServiceImp fileService;
	@Mock 
	FileService fileServiceMock;
	@InjectMocks
	FileUploadController controller;
	
	private MockMvc mockMvc;
	private File file;
	private MultipartFile mpFile;
	private List<File> testRes = new ArrayList<>();
	
	
	@Before
	public void initTest(){
		file = new File("fakeName", new Date(),100, "fakePath");
		mpFile = new MockMultipartFile("fakeName", "nonsensecontent".getBytes());
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void testGetFile(){
		when(fileDao.getFile(file.getId())).thenReturn(file);
		assertEquals(fileService.getFile(file.getId()).hashCode(), file.hashCode());
		assertEquals(fileService.getFile(file.getId()).toString(), file.toString());
	}


	@Test
	public void testSaveFile() throws IOException{
		when(FileDao.save(file)).thenReturn(file);
		assertEquals(fileService.getFile(file.getId()).hashCode(), file.hashCode());
		assertEquals(fileService.getFile(file.getId()).toString(), file.toString());
		
	}
	
	@Test
	public void testGetAll(){
		testRes.add(file);
		when(fileDao.getAll()).thenReturn(testRes);
		assertTrue(fileService.getAllFiles().size() == 1 && 
				fileService.getAllFiles().get(0).toString().equals(testRes.get(0).toString()));
	}
	
	
	@Test(expected = FileUploadException.class)
	public void testGetFileWithException() throws Exception{
		when(fileServiceMock.getFile(100)).thenReturn(null);
		controller.getFileInfo(100);
	}
}
