/**
	时间：2018年4月18日
	地点：
	包名：com.example.demo.web
	项目名：grap
 * 
 */
package com.example.demo.web;

import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.example.demo.system.mysql.service.impl.SourceService;

/**
 * @author Administrator
 *
 */
@Controller
public class FileUtilController {
	@Resource
	private SourceService sourceService;
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	
}
