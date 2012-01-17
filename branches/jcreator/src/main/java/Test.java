import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.citrus.service.template.TemplateNotFoundException;
import com.alifi.jgenerator.freemarker.FreeMarkerConfig;
import com.alifi.jgenerator.freemarker.StringTemplateLoader;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FreeMarkerConfig freemarker = new FreeMarkerConfig(new StringTemplateLoader());
		Map<String,String> rootMap = new HashMap<String,String>();
		rootMap.put("name", "王德峰");
		StringWriter  writer = freemarker.process("${name}${name}", rootMap);
		System.out.println(writer.toString());
		FreeMarkerConfig freemarkerTemplate = new FreeMarkerConfig();
		try{
		writer = freemarkerTemplate.process("test.ftl", rootMap);
		System.out.println(writer.toString());
		}catch(TemplateNotFoundException e){
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
