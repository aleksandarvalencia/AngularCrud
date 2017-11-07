/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.controller;

import com.projects.angularcrud.model.FileInfo;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.projects.angularcrud.service.StorageService;
import java.io.IOException;
//import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
//import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
//import org.springframework.util.StringUtils;

@RestController
public class UploadController {

	@Autowired
	StorageService storageService;
        @Autowired
        ServletContext context;
	List<String> files = new ArrayList<>();

        @RequestMapping(value = "/uploadfile", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

        @RequestMapping(value = "/fileupload", headers=("content-type=multipart/*"), method = RequestMethod.POST)
        public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile, HttpServletResponse response) {
         FileInfo fileInfo = new FileInfo();
         HttpHeaders headers = new HttpHeaders();
         if (!inputFile.isEmpty()) {
          try {
           String originalFilename = inputFile.getOriginalFilename();
           File destinationFile = new File("D:\\AngularProjects\\uploaded" +  File.separator + originalFilename);
           
           inputFile.transferTo(destinationFile);
           fileInfo.setFileName(destinationFile.getPath());
           fileInfo.setFileSize(inputFile.getSize());
           headers.add("File Uploaded Successfully - ", originalFilename);
           return new ResponseEntity<>(fileInfo, headers, HttpStatus.OK);
          } 
          catch (IOException | IllegalStateException e) 
          {    
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }
         }else{
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
 }
 
        
        @RequestMapping(value = "/getallfiles", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FileInfo>> getListFiles(Model model) throws IOException {
            
                /*String fileName="";
                fileName=MvcUriComponentsBuilder.fromMethodName(UploadController.class, "getFile", fileName).build().toString();
		List<String> fileNames = new ArrayList<>();
                fileNames=files.stream().map(fileName.toString()).collect(Collectors.toList());*/
            
            
                /*List<Object> fileNames = new ArrayList<>();
                Path filesLocation = Paths.get(context.getRealPath("/WEB-INF/uploaded"));
                fileNames=(Files.find(filesLocation, 1, null, (FileVisitOption) null).collect(Collectors.toList()));*/
                
                //Path filesLocation = Paths.get(context.getRealPath("/WEB-INF/uploaded"));
                Path filesLocation = Paths.get("D:\\AngularProjects\\uploaded");
                final List<FileInfo> fi = new ArrayList<>();
                
                try {
                    Files.walkFileTree(filesLocation, new SimpleFileVisitor<Path>()
                    {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            if (attrs.isDirectory()) {
                                return FileVisitResult.CONTINUE;
                            }
                            FileInfo filei=new FileInfo();
                            //String id = file.getFileName().toString();
                            filei.setFileName(file.getFileName().toFile().getName());
                            filei.setFileSize(file.getFileName().toFile().length());
                            fi.add(filei);
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) 
                {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
               
		return new ResponseEntity<>(fi, HttpStatus.OK);
	}

        @RequestMapping(value ="/files/{filename:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
                
                HttpHeaders header=new HttpHeaders();
                header.set("Content-Disposition","attachment; filename=\"" + file.getFilename() + "\"");
		return new ResponseEntity<>(file,header,HttpStatus.OK);
				
	}
}