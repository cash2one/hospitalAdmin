package com.jumper.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.NewsMessage;
import com.jumper.hospital.service.NewsMyMessageSevice;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.JsonUtils;
import com.jumper.hospital.vo.VOMessage;

@Controller
@RequestMapping(value = "/myMessageService")
public class MyMessageController extends BaseController  {
	@Autowired
	private NewsMyMessageSevice NewsMyMessageSeviceImpl;
	/**
	 * 我的消息
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/index")
	public String newsMessageIndex(ModelMap model,Page<NewsMessage> page,String searchKey){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Integer hospId=hospitalInfo.getId();
		Page<NewsMessage> pages = NewsMyMessageSeviceImpl.list(page,hospId, searchKey);
		List<NewsMessage> news = pages.getResult();
		//model到页面显示
		model.put("news", news);
		model.put("page", pages);
		model.put("searchKey", searchKey);
		//配置管理员列表  前端界面控制
		pageLocation(model, Consts.INFORMATION_MODULE, "message", "list");
		return "news/myMessage";
	}
	
	@RequestMapping(value = "/modify")
	public String newsModify(String  id,String content,String isDelete){
		NewsMessage news=new NewsMessage();
		news.setContent(content);
		news.setIsDelete(Integer.parseInt(isDelete));
		this.NewsMyMessageSeviceImpl.addOrUpdate(news,id);
		return "redirect:/myMessageService/index";
	}
	@RequestMapping(value = "/modifyAndPush")
	public String newsmodifyAndPush(String  id,String title,String content,String isDelete,String pushStatus){
		NewsMessage news=new NewsMessage();
		news.setContent(content);
		news.setIsDelete(Integer.parseInt(isDelete));
		newsPush(id);//推送消息
		this.NewsMyMessageSeviceImpl.addOrUpdate(news,id);//保存消息
	    return "redirect:/myMessageService/index";
	}
	@ResponseBody
	@RequestMapping("/delete")
	public String newsDelete(String  id){
		if(id!=null&&!"".equals(id)){
			boolean isSuccess = NewsMyMessageSeviceImpl.deleteNewsMyMessage(new Integer(id));
			if(isSuccess == true){
				return "Y";
			}else{
				return "N";
			}
		}
		return "N";
		
	}
	@RequestMapping("/push")
	public String newsPush(String id){
		//查询当前记录
		NewsMessage message=this.NewsMyMessageSeviceImpl.editNewsMessage(new Integer(id));  
		String title=message.getTitle();
		String content=message.getContent();
		//调取接口推送
	    String result= HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id="+id+"&content=+"+content+"&title="+title+"&language=cn&Msg_type=14&user_type=2&user_msg="+9999);
		JsonUtils.render(getResponse(), result);
		return "redirect:/myMessageService/index";
	}
	
	@RequestMapping("/modifyList")
	public void modifyList(String id) {
		if(StringUtils.isNotBlank(id)){
			NewsMessage vo=NewsMyMessageSeviceImpl.editNewsMessage(new Integer(id));
			VOMessage data=new VOMessage();
			data.setTitle(vo.getTitle());
			data.setContent(vo.getContent());
			JsonUtils.render(getResponse(), data);
		}
	}
	@RequestMapping(value="/userPush",method = {RequestMethod.POST,RequestMethod.GET})
	public String userPush(@RequestParam(value = "preganyWeek", required = true)String preganyWeek, HttpServletRequest request){
		String arrayPreganyWeek[]=preganyWeek.split(",");
	    List<VOMessage > list=new ArrayList<VOMessage>();
	    VOMessage vo=null;
	    for(int i=0;i<arrayPreganyWeek.length;i++){
	    	vo=new VOMessage();
	    	vo.setTitle(arrayPreganyWeek[i]);
	    	list.add(vo);
	    }
		request.setAttribute("list", list);
		JsonUtils.render(getResponse(), list);
		return "hospital/userList";
	}
}
