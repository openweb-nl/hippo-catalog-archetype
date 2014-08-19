#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.componentsinfo;

import net.sourceforge.hstmixinsupport.annotations.JcrPath;
import net.sourceforge.hstmixinsupport.annotations.Mixin;

import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

import com.tdclighthouse.prototype.componentsinfo.MixinEnabled;

@FieldGroupList({ @FieldGroup(titleKey = "group.title", value = { "title", "tryToUseMixin" }), })
@Mixin("${namespace}:${widgetName}Mixin")
public interface ${widgetName}Info extends MixinEnabled {

    @Parameter(defaultValue = "${widgetName}", name = "title")
    @JcrPath("${namespace}:title")
    public String getTitle();

}
