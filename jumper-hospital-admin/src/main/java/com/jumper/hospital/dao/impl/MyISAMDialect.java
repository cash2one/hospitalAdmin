/**
 * 
 */
package com.jumper.hospital.dao.impl;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;


/**
 * @author huangcr
 *
 * 2017-7-13
 */
public class MyISAMDialect extends MySQL5Dialect {  
    
    public MyISAMDialect(){  
        super();  
        registerFunction("convert_mine", new SQLFunctionTemplate(StringType.INSTANCE, "convert(?1 using gbk)"));  
    }  
    @Override  
    public String getTableTypeString() {  
        return "engine=MyISAM";  
    }  
} 
