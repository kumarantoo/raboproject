package com.rabobank.upload.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.rabobank.upload.model.Records;
import com.rabobank.upload.service.FileUpload;
import lombok.extern.slf4j.Slf4j;

/**
 * UploadContoller this class all controller mapping and upload service call logics
 * @author kumaran
 *
 */
@Controller
@Slf4j
public class UploadContoller {

	@Autowired
	private FileUpload storageService;

	/**
	 * Contoller method to get welcome page upload form html once the tomcat is booted
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		log.info("Getting welcome page");
		return "uploadForm";
	}

	/**
	 * Contoller method will handle all file upload and save it under project directory
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		log.info("Getting handleFileUpload to upload filed");
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/";
	}

	/**
	 * Contoller method to get the data from uploaded files from the path or replace the file
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/getFiles.json")
	public  @ResponseBody String getData(Model model) throws IOException {
		log.info("Getting getData to from file");
		List<Records> list = storageService.getFileRecords();
		Gson gson = new Gson();
		String jsonCartList = gson.toJson(list);
		log.info("End getData to from file::"+jsonCartList);
		return jsonCartList;
	}
}
