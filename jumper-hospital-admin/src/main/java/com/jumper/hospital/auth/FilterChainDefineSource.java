package com.jumper.hospital.auth;
/**
 * 实现自定义shiro权限类
 * @author rent
 * @date 2015-06-19
 */
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.service.AuthorityService;

public class FilterChainDefineSource implements FactoryBean<Ini.Section> {

	private String filterChainDefinitions;
	@Autowired
	private AuthorityService moduleServiceImpl;
	
	public static final String PREMISSIOM_STRING = "perms[\"{0}\"]";
	
	@SuppressWarnings({ "static-access"})
	public Section getObject() throws Exception {
		List<Authority> modules = moduleServiceImpl.findAll();
		
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(ini.DEFAULT_SECTION_NAME);
		if(modules != null && modules.size() > 0){
			for(Authority module : modules){
				if(StringUtils.isNotEmpty(module.getUrl()) && StringUtils.isNotEmpty(module.getAuthority())){
					section.put(module.getUrl(), MessageFormat.format(PREMISSIOM_STRING, module.getAuthority()));
				}
			}
		}
		section.put("/**", "authc");
		return section;
	}
	
	public void setFilterChainDefinitions(String filterChainDefinitions){
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}

}
