#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId};

import java.util.Map;

import javax.servlet.ServletContext;

import ${groupId}.components.${widgetName};
import ${groupId}.componentsinfo.${widgetName}Info;

import org.easymock.EasyMock;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.mock.core.component.MockHstRequest;
import org.hippoecm.hst.mock.core.component.MockHstResponse;
import org.hippoecm.hst.mock.core.request.MockComponentConfiguration;
import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.hippoecm.hst.utils.ParameterUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdclighthouse.prototype.utils.BeanUtils.BeanUtilsMockBean;
import com.tdclighthouse.prototype.utils.Constants.AttributesConstants;

public class ${widgetName}Test {

    private static final String TITLE = "title";
    private static final String TITLE_FROM_MIXIN = "title from document";
    private ${widgetName} ${widgetNameLowercased};

    @Before
    public void init() {
        ${widgetNameLowercased} = new ${widgetName}();
        ServletContext servletContext = EasyMock.createNiceMock(ServletContext.class);
        ${widgetNameLowercased}.init(servletContext, new MockComponentConfiguration());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void dontTryMixinTestCase() {

        MockHstRequest request = createMockRequest(TITLE, false, null);
        ${widgetNameLowercased}.doBeforeRender(request, new MockHstResponse());
        Map<String, Object> model = (Map<String, Object>) request.getAttribute(AttributesConstants.MODEL);
        ${widgetName}Info paramInfo = (${widgetName}Info) model.get(AttributesConstants.PARAM_INFO);
        Assert.assertEquals(TITLE, paramInfo.getTitle());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void dontTryMixinNullTestCase() {

        MockHstRequest request = createMockRequest(TITLE, null, null);
        ${widgetNameLowercased}.doBeforeRender(request, new MockHstResponse());
        Map<String, Object> model = (Map<String, Object>) request.getAttribute(AttributesConstants.MODEL);
        ${widgetName}Info paramInfo = (${widgetName}Info) model.get(AttributesConstants.PARAM_INFO);
        Assert.assertEquals(TITLE, paramInfo.getTitle());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void tryMixinWithMixinTestCase() {
        BeanUtilsMockBean contentBean = new BeanUtilsMockBean(TITLE_FROM_MIXIN, false);
        MockHstRequest request = createMockRequest(TITLE, true, contentBean);
        ${widgetNameLowercased}.doBeforeRender(request, new MockHstResponse());
        Map<String, Object> model = (Map<String, Object>) request.getAttribute(AttributesConstants.MODEL);
        ${widgetName}Info paramInfo = (${widgetName}Info) model.get(AttributesConstants.PARAM_INFO);
        Assert.assertEquals(TITLE_FROM_MIXIN, paramInfo.getTitle());
        Assert.assertEquals(contentBean, model.get(AttributesConstants.DOCUMENT));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void tryMixinWithoutMixinTestCase() {
        MockHstRequest request = createMockRequest(TITLE, true, null);
        ${widgetNameLowercased}.doBeforeRender(request, new MockHstResponse());
        Map<String, Object> model = (Map<String, Object>) request.getAttribute(AttributesConstants.MODEL);
        ${widgetName}Info paramInfo = (${widgetName}Info) model.get(AttributesConstants.PARAM_INFO);
        Assert.assertEquals(TITLE, paramInfo.getTitle());
    }

    @Test
    public void tryMixinExceptionTestTestCase() {
        MockHstRequest request = createMockRequest(TITLE, true, new BeanUtilsMockBean(null, true));
        boolean hstComponentExceptionWasThrown = false;
        try {
            ${widgetNameLowercased}.doBeforeRender(request, new MockHstResponse());
        } catch (HstComponentException e) {
            hstComponentExceptionWasThrown = true;
        }
        Assert.assertTrue(hstComponentExceptionWasThrown);
    }

    private MockHstRequest createMockRequest(String parameterInfoTitle, Boolean useMixin, BeanUtilsMockBean contentBean) {
        MockHstRequest request = new MockHstRequest();
        MockHstRequestContext requestContext = new MockHstRequestContext();
        requestContext.setContentBean(contentBean);
        request.setRequestContext(requestContext);
        request.setAttribute(ParameterUtils.PARAMETERS_INFO_ATTRIBUTE,
                creatMockParameterInfo(parameterInfoTitle, useMixin));
        return request;
    }

    private ${widgetName}Info creatMockParameterInfo(final String title, final Boolean useMixin) {
        return new ${widgetName}Info() {

            @Override
            public Boolean getUseMixin() {
                return useMixin;
            }

            @Override
            public String getTitle() {
                return title;
            }
        };
    }

}
