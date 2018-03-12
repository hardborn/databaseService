package com.sitop.smart365.dataservice.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.Labels;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sitop.smart365.dataservice.model.DataProcessingConfiguration;

import java.util.List;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;
    @JSONField(serialize = false)
    private String filterLabel;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
    public String getFilterLabel() {
        return filterLabel;
    }

    public Result setFilterLabel(String filterLabel) {
        this.filterLabel = filterLabel;
        return  this;
    }


    @Override
    public String toString() {
        //JSONSerializer serializer = new JSONSerializer();
        //serializer.config(SerializerFeature.SkipTransientField, false);
        //serializer.config(SerializerFeature.WriteMapNullValue, true);
        //serializer.addFilter(Labels.excludes(this.filterLabel));
        //serializer.write(this);
        //return serializer.toString();
        String str = null;

        if (this.filterLabel == null){
           str = JSON.toJSONString(this,SerializerFeature.WriteMapNullValue);
        }else{
            str = JSON.toJSONString(this, Labels.excludes(this.filterLabel),SerializerFeature.WriteMapNullValue  );
        }
        return str;
    }


}
