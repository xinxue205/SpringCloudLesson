package com.lynn;

import com.lynn.version.ApiVersion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("{version}")
@ApiVersion(1)
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    String port;
    @Autowired
    private Aliyun aliyun;
    @RequestMapping("/hello")
    public String home(String name) {
        return "hi "+name+",i am from port:" +port+aliyun;
    }

    @RequestMapping("authorize")
    public ResponseEntity authorize(@Valid AuthorizeIn authorize, BindingResult result){
//        if(result.hasFieldErrors()){
//            List<FieldError> errorList = result.getFieldErrors();
//            //通过断言抛出参数不合法的异常
//            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
//        }
    	Map<String, String> map  = new HashMap<String, String>();
		map.put("result", "ok");
		map.put("data_id", "1");;
		if(result.hasErrors()){
			 List<FieldError> fieldErrors = result.getFieldErrors();
			 fieldErrors.forEach(fieldError -> {
                   //日志打印不符合校验的字段名和错误提示
				 LOG.error("error field is : {} ,message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
                 });
			 for(int i=0;i<fieldErrors.size();i++){
                    //控制台打印不符合校验的字段名和错误提示
			System.out.println("error field is :"+fieldErrors.get(i).getField()+",message is :"+fieldErrors.get(i).getDefaultMessage());
			 }
			// pesponsibles.setError_msg(fieldErrors);
			return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(map, HttpStatus.OK);
    }

    @ExceptionHandler
    public String doError(Exception ex) throws Exception{
        ex.printStackTrace();
        return ex.getMessage();
    }

}