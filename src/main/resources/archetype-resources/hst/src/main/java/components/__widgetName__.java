#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.components;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import ${package}.componentsinfo.${widgetName}Info;

import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;

import com.tdclighthouse.prototype.utils.BeanUtils;
import com.tdclighthouse.prototype.utils.Constants.AttributesConstants;

@ParametersInfo(type = ${widgetName}Info.class)
public class ${widgetName} extends BaseHstComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        try {
            Map<String, Object> model = new HashMap<String, Object>();
            HippoBean bean = request.getRequestContext().getContentBean();
            ${widgetName}Info paramInfo = getParamInfo(request, bean);
            model.put(AttributesConstants.PARAM_INFO, paramInfo);
            model.put(AttributesConstants.DOCUMENT, bean);
            request.setAttribute(AttributesConstants.MODEL, model);
        } catch (RepositoryException e) {
            throw new HstComponentException(e.getMessage(), e);
        }
    }

    private ${widgetName}Info getParamInfo(HstRequest request, HippoBean bean) throws RepositoryException {
        ${widgetName}Info paramInfo = getComponentParametersInfo(request);
        Boolean useMixin = paramInfo.getUseMixin();
        if (useMixin != null && useMixin) {
            HippoBean mixinProxy = BeanUtils.getMixinProxy(bean);
            if (mixinProxy instanceof ${widgetName}Info) {
                paramInfo = (${widgetName}Info) mixinProxy;
            }
        }
        return paramInfo;
    }

}
