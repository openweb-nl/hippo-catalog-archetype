#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.tdclighthouse.prototype.utils;

import javax.jcr.RepositoryException;

import ${package}.componentsinfo.${widgetName}Info;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;

public class BeanUtils {
    public static HippoBean getMixinProxy(HippoBean bean) throws RepositoryException {
        HippoBean result = null;
        if (bean instanceof BeanUtilsMockBean) {
            BeanUtilsMockBean mockBean = (BeanUtilsMockBean) bean;
            if (mockBean.isThrowRepositoryException()) {
                throw new RepositoryException();
            } else {
                result = new MockProxy(mockBean);
            }
        }
        return result;
    }

    public static class MockProxy extends HippoDocument implements ${widgetName}Info {
        private final BeanUtilsMockBean bean;

        public MockProxy(BeanUtilsMockBean bean) {
            this.bean = bean;
        }

        @Override
        public Boolean getUseMixin() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getTitle() {
            return bean.getTitle();
        }

    }

    public static class BeanUtilsMockBean extends HippoDocument {

        private final String title;
        private final boolean throwRepositoryException;

        public BeanUtilsMockBean(String title, boolean throwRepositoryException) {
            this.title = title;
            this.throwRepositoryException = throwRepositoryException;
        }

        public String getTitle() {
            return title;
        }

        public boolean isThrowRepositoryException() {
            return throwRepositoryException;
        }

    }
}
