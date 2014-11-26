package com.mumtel.util;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileuploadForm implements Serializable{
	private CommonsMultipartFile fileData;

	  public CommonsMultipartFile getFileData()
	  {
	    return fileData;
	  }

	  public void setFileData(CommonsMultipartFile fileData)
	  {
	    this.fileData = fileData;
	  }
}
