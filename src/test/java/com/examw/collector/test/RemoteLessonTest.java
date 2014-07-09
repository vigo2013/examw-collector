package com.examw.collector.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.examw.collector.domain.Catalog;
import com.examw.collector.domain.Pack;
import com.examw.collector.domain.Relate;
import com.examw.collector.domain.SubClass;
import com.examw.collector.domain.local.TeacherEntity;
import com.examw.collector.service.IDataServer;
import com.examw.collector.service.IRemoteDataProxy;
import com.thoughtworks.xstream.XStream;

/**
 * 远程课程数据测试。
 * @author yangyong.
 * @since 2014-06-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-examw-collector.xml"})
public class RemoteLessonTest {
	@Resource
	private IDataServer dataServer;
	@Resource 
	private IRemoteDataProxy remoteDataProxy;
	/**
	 * 加载课程数据。
	 * @throws IOException 
	 */
	//@Test
	public void loadCataData() throws IOException{
		System.out.print("开始获取课程类别：");
		List<Catalog> list = this.dataServer.loadCatalogs();
		
		XStream xStream = new XStream();
		String xml = xStream.toXML(list);
		System.out.print(xml);
	}
	//@Test
	public void loadClasses(){
		List<SubClass>  list = this.dataServer.loadClasses("1905", null);
		for(SubClass s:list){
			System.out.println(s.getSubject().getCode());
		}
		XStream xStream = new XStream();
		String xml = xStream.toXML(list);
		System.out.print(xml);
	}
	//@Test
	public void loadRelates(){
		List<Relate> list = this.dataServer.loadRelates("491");
		XStream xStream = new XStream();
		String xml = xStream.toXML(list);
		System.out.print(xml);
	}
	//@Test
	public void loadPacks(){
		List<Pack> list = this.dataServer.loadPacks("700", "700");
		XStream xStream = new XStream();
		String xml = xStream.toXML(list);
		System.out.print(xml);
	}
	//@Test
	public void loadTest() throws Exception{
		String xml = this.remoteDataProxy.loadTeacher("435");
		xml = xml.replaceAll("\\s", "");
		System.out.println(xml);
		Pattern teacherName = Pattern.compile("<h2><dl><dt>(.+)</dt><ddclass=\"ms_itdt_dd1\"(.+)");
		Pattern teacherLessons = Pattern.compile("<dt><strong>(.+)</strong></dt><ddstyle=(.*)><fonttitle=(.*)>(.*)</font></dd></dl>");//</dt><ddstyle=(.+)<fonttile=(.+)>(.+)</font></dd></dl>");
		Pattern teacherInfo = Pattern.compile("<ul><li><pclass=\"dd_p_1center\">(.+)</p></li></ul>");
		Pattern teacherImg = Pattern.compile("<imgsrc=\"([/\\w\\d.]+)\"width=\"479px");
		Pattern teacherImpress = Pattern.compile("<liclass=\"([\\w_\\d]*)\"data-bind=\"attr:([{'\\w:}]+),text:val\">(.{4})</li>");
//		String s = "<h2><dl><dt>王全一老师</dt><dd class='ms_itdt_dd1'></dd><dd class='ms_itdt_dd2'><a href='#004'><img src='images2011/pic_10.gif' width='69' height='21' /></a></dd></dl></h2>";
//		String s2 = "<dt><strong>主要课程：</strong></dt><ddstyle='width:290px;line-height:20px;padding-top:7px;'><fonttitle='人力资源管理，人力资源实训'>人力资源管理，人力资源实训</font></dd></dl>";
//		String s3 = "<ul><li><pclass=\"dd_p_1center\">● 国家劳动部认定的人力资源管理师培训师资，职业资格认证教材编审专家；<Br>● 国际注册管理咨询师、国家职业指导师。<Br>● 兼任管理咨询公司项目策划人和多家公司管理和培训顾问。<Br>● 历任高校企业管理专业教师、企业人力资源总监、企业集团副总裁。<Br>● 私营公司董事长兼CEO、企业人力资源课程开发设计师等。<Br>● 主编《企业人力资源管理师应试宝典》，2007年列入全国管理咨询师500名。<Br>● 多年丰富的教学和实践经验，在人力资源师辅导界享有很高的评价。<Br></p></li>";
//		String s4 = "<imgsrc=\"/web_teacher/images/20111229144831.jpg\"width=\"479px\"height=\"300px\"/></li><imgsrc=\"/web_teacher/images/20111229144831.jpg\"width=\"479px\"height=\"300px\"/></li>";
//		String s5 = "<liclass=\"\"data-bind=\"attr:{'class':strclass},text:val\">生动翔实</li><liclass=\"margin_0\"data-bind=\"attr:{'class':strclass},text:val\">博学多才</li>";
	    Matcher m2 = teacherName.matcher(xml);//group(1);
	    m2 = teacherLessons.matcher(xml); //group(4);
	    m2 = teacherInfo.matcher(xml);	//group(1);
	    m2 = teacherImg.matcher(xml);	//group(1);
	    m2 = teacherImpress.matcher(xml); //group(3)
	    while(m2.find()){  
	    //通过调用group()方法里的索引 将url,标题和日期全部给打印出来  
	       System.out.println("打印出url链接:"+m2.group(3));  
	       //System.out.println("打印出标题:"+m2.group(2));   
	       //System.out.println("打印出日期:"+m2.group(3));   
	      // System.out.println("打印出日期:"+m2.group(4));   
	      // System.out.println();  
	    }    
	}
	//@Test
	public void downloadPicTest() throws Exception{
		String url = "http://www.edu24ol.com//web_teacher/images/20111229144831.jpg";
		String path = url;
		String savePath = "F:/tempp/";
		String fileName = url.substring(url.lastIndexOf("/")+1);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String filePath = null;
		if(savePath.endsWith("/")){
			filePath = savePath + File.separator + date;
		}else{
			filePath = savePath + date;
		}
		if(!new File(filePath).exists()){
			new File(filePath).mkdirs();
		}
		try{  
			java.net.HttpURLConnection conn =(java.net.HttpURLConnection) new URL(url).openConnection();  
            if (conn.getResponseCode() == 200) {  
              
                java.io.InputStream is = (java.io.InputStream) conn.getInputStream();  
                try{  
                    String realPath = filePath+File.separator+fileName;  
                    FileOutputStream baos = new FileOutputStream(new File(realPath));  
                    int buffer = 1024;  
                    byte[] b = new byte[buffer];  
                    int n = 0;  
                    while ((n = is.read(b, 0, buffer)) > 0) {  
                        baos.write(b, 0, n);  
                    }  
                    is.close();  
                    baos.close();  
                }catch(Exception e){  
                	e.printStackTrace();
                }  
                path = File.separator+date+File.separator+fileName;
            }else{  
            }  
        }catch(Exception e){  
                e.printStackTrace();  
        }  
		System.out.println(path);
	}
	@Test
	public void loadTeacherTest()throws Exception{
		TeacherEntity t = this.dataServer.loadTeacher("221");
		if(t!=null)
		{
			System.out.println(t.getId());
			System.out.println(t.getName());
			System.out.println(t.getImgurl());
			System.out.println(t.getDescription());
			System.out.println(t.getLessons());
		}
	}
}