package com.company.project.android.bean;

import java.io.Serializable;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.bean
 * @class describe
 * @time 2018/3/12 17:38
 * @change
 * @class describe
 */

public class DataResponse<E,F,T> implements Serializable{
    /**
     * code	200
     app_id	"ld_test"
     app_secret	"a123456"
     total_count	10
     mark	"LINDAO_835570"
     */
    private int code;
    private String app_id;
    private String app_secret;
    private int total_count;
    private String mark;
    private E cpt_data;
    private F cmp_data;
    private T dsp_data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public E getCpt_data() {
        return cpt_data;
    }

    public void setCpt_data(E cpt_data) {
        this.cpt_data = cpt_data;
    }

    public F getCmp_data() {
        return cmp_data;
    }

    public void setCmp_data(F cmp_data) {
        this.cmp_data = cmp_data;
    }

    public T getDsp_data() {
        return dsp_data;
    }

    public void setDsp_data(T dsp_data) {
        this.dsp_data = dsp_data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataResponse{");
        sb.append("code=").append(code);
        sb.append(", app_id='").append(app_id).append('\'');
        sb.append(", app_secret='").append(app_secret).append('\'');
        sb.append(", total_count=").append(total_count);
        sb.append(", mark='").append(mark).append('\'');
        sb.append(", cpt_data=").append(cpt_data);
        sb.append(", cmp_data=").append(cmp_data);
        sb.append(", dsp_data=").append(dsp_data);
        sb.append('}');
        return sb.toString();
    }
}
