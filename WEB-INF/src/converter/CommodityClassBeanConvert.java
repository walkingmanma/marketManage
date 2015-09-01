package converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import bean.CommodityClassBean;

public class CommodityClassBeanConvert extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		CommodityClassBean ccn=new CommodityClassBean();
		//只处理请求参数数组第一个数组元素
		//并将该字符创以逗号分割成多个字符创
		String[] typeValue=arg1[0].split(",");
		ccn.setTypeid(Integer.valueOf(typeValue[0]));
		ccn.setTypename(typeValue[1]);
		ccn.setTypedesc(typeValue[2]);
		return ccn;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		return null;
	}

}
