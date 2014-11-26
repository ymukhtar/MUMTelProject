package com.mumtel.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileuploadForm {
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
