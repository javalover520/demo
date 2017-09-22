package io.renren.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FreemarkerShiro implements TemplateMethodModelEx {

	public Object exec(List args) throws TemplateModelException {
		if(args.size() != 1){
            throw new TemplateModelException("Wrong arguments");
        }
		String perm=String.valueOf(args.get(0));
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(perm);
	}

}
