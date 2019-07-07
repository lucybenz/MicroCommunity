package com.java110.web.smo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.java110.common.constant.PrivilegeCodeConstant;
import com.java110.common.constant.ServiceConstant;
import com.java110.common.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.java110.core.context.IPageData;
import com.java110.web.core.AbstractComponentSMO;
import com.java110.web.smo.service.IAddServiceSMO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * 添加小区服务实现类
 * add by wuxw 2019-06-30
 */
@Service("addServiceSMOImpl")
public class AddServiceSMOImpl extends AbstractComponentSMO implements IAddServiceSMO {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void validate(IPageData pd, JSONObject paramIn) {

        //super.validatePageInfo(pd);

        //Assert.hasKeyAndValue(paramIn, "xxx", "xxx");
        Assert.hasKeyAndValue(paramIn, "name", "必填，请填写服务名称");
Assert.hasKeyAndValue(paramIn, "serviceCode", "必填，请填写服务编码如 service.saveService");
Assert.hasKeyAndValue(paramIn, "businessTypeCd", "可填，请填写秘钥，如果填写了需要加密传输");
Assert.hasKeyAndValue(paramIn, "seq", "必填，请填写序列");
Assert.hasKeyAndValue(paramIn, "isInstance", "可填，请填写实例 Y 或N");
Assert.hasKeyAndValue(paramIn, "method", "必填，请填写调用方式");
Assert.hasKeyAndValue(paramIn, "timeout", "必填，请填写超时时间");
Assert.hasKeyAndValue(paramIn, "retryCount", "必填，请填写重试次数");
Assert.hasKeyAndValue(paramIn, "provideAppId", "必填，请填写提供服务");



        super.checkUserHasPrivilege(pd, restTemplate, PrivilegeCodeConstant.AGENT_HAS_LIST_SERVICE);

    }

    @Override
    protected ResponseEntity<String> doBusinessProcess(IPageData pd, JSONObject paramIn) {
        ResponseEntity<String> responseEntity = null;
        super.validateStoreStaffCommunityRelationship(pd, restTemplate);

        responseEntity = this.callCenterService(restTemplate, pd, paramIn.toJSONString(),
                ServiceConstant.SERVICE_API_URL + "/api/service.saveService",
                HttpMethod.POST);
        return responseEntity;
    }

    @Override
    public ResponseEntity<String> saveService(IPageData pd) {
        return super.businessProcess(pd);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
