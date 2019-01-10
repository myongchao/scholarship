package com.myc.scholarship.mian.entity;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.myc.scholarship.entity.PageDto;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/10$ 10:12$
 */
public class CommonSearchDto<T>{
    private String search;
    private PageDto page;
    private Map<String, Object> form;
    private Map<String, String> op;
    private Map<String, String> con;
    private String orderBy;

    public Pagination getPagination() {
        return this.page == null ? new Pagination(0, 10) : new Pagination(this.page.getCurrent(), this.page.getPageCount());
    }

    public Page<T> getPlusPage() {
        Pagination pagination = this.getPagination();
        return new Page(pagination.getCurrent(), pagination.getSize());
    }

    public Wrapper<T> formToEntityWrapperWithSearch(String[] columns, String preStr) throws JSONException {
        return this.createWrapperWithSearch(columns, preStr, (Map)null);
    }

    public Wrapper<T> formToEntityWrapperWithSearch(String[] columns, String preStr, Map<String, String> pres) throws JSONException {
        return this.createWrapperWithSearch(columns, preStr, pres);
    }

    public Wrapper<T> formToEntityWrapper(String preStr) throws JSONException {
        return this.createWrapper(preStr, (Map)null);
    }

    public Wrapper<T> formToEntityWrapper(String preStr, Map<String, String> pres) throws JSONException {
        return this.createWrapper(preStr, pres);
    }

    private Wrapper<T> createWrapperWithSearch(String[] columns, String preStr, Map<String, String> pres) throws JSONException {
        Wrapper<T> wrapper = this.createWrapper(preStr, pres);
        if (this.search != null && !"".equals(this.search)) {
            List<String> ors = new ArrayList();

            for(int i = 0; i < columns.length; ++i) {
                String key = columns[i];
                String column = Convert.humpToUnderline(columns[i]);
                column = this.getColumn(pres, key, preStr);
                ors.add(column + " like '" + this.search + "'");
            }

            wrapper = wrapper.and("(" + String.join(" or ", ors) + ")", new Object[0]);
        }

        return wrapper;
    }

    private Wrapper<T> createWrapper(String preStr, Map<String, String> pres) throws JSONException {
        Wrapper<T> ew = new EntityWrapper();
        String key;
        String curOp;
        if (this.form != null) {
            Iterator var4 = this.form.keySet().iterator();

            while(var4.hasNext()) {
                key = (String)var4.next();
                if (this.op != null && this.op.containsKey(key)) {
                    curOp = (String)this.op.get(key);
                } else {
                    curOp = "=";
                }

                String curCon;
                if (this.con != null && this.con.containsKey(key)) {
                    curCon = (String)this.con.get(key);
                } else {
                    curCon = "and";
                }

                if (!curCon.equals("or")) {
                    ew = ((Wrapper)ew).and();
                } else {
                    ew = ((Wrapper)ew).or();
                }

                String column = this.getColumn(pres, key, preStr);
                Object val = this.form.get(key);
                byte var11 = -1;
                switch(curOp.hashCode()) {
                    case 60:
                        if (curOp.equals("<")) {
                            var11 = 2;
                        }
                        break;
                    case 61:
                        if (curOp.equals("=")) {
                            var11 = 0;
                        }
                        break;
                    case 62:
                        if (curOp.equals(">")) {
                            var11 = 1;
                        }
                        break;
                    case 1921:
                        if (curOp.equals("<=")) {
                            var11 = 4;
                        }
                        break;
                    case 1983:
                        if (curOp.equals(">=")) {
                            var11 = 3;
                        }
                        break;
                    case 3365:
                        if (curOp.equals("in")) {
                            var11 = 6;
                        }
                        break;
                    case 97425:
                        if (curOp.equals("bet")) {
                            var11 = 7;
                        }
                        break;
                    case 3321751:
                        if (curOp.equals("like")) {
                            var11 = 5;
                        }
                }

                switch(var11) {
                    case 0:
                        ew = ((Wrapper)ew).eq(column, val);
                        break;
                    case 1:
                        ew = ((Wrapper)ew).gt(column, val);
                        break;
                    case 2:
                        ew = ((Wrapper)ew).lt(column, val);
                        break;
                    case 3:
                        ew = ((Wrapper)ew).ge(column, val);
                        break;
                    case 4:
                        ew = ((Wrapper)ew).le(column, val);
                        break;
                    case 5:
                        ew = ((Wrapper)ew).like(column, val.toString());
                        break;
                    case 6:
                        ew = ((Wrapper)ew).in(column, (Collection<?>) val);
                        break;
                    case 7:
                        JSONArray args = (JSONArray)val;
                        ew = ((Wrapper)ew).between(column, args.get(0), args.get(1));
                }
            }
        }

        if (this.orderBy != null) {
            String[] orderByInfo = this.orderBy.split(" ");
            key = orderByInfo[0];
            curOp = this.getColumn(pres, key, preStr);
            if (orderByInfo.length >= 2 && orderByInfo[1].toLowerCase().equals("asc")) {
                ew = ((Wrapper)ew).orderBy(curOp, true);
            } else {
                ew = ((Wrapper)ew).orderBy(curOp, false);
            }
        }

        return (Wrapper)ew;
    }

    private String getColumn(Map<String, String> pres, String key, String def) {
        if (null != key && "" != key && key.indexOf(".") > -1) {
            return Convert.humpToUnderline(key);
        } else {
            String pre = "";
            if (def != null) {
                pre = def;
            }

            if (pres != null && pres.containsKey(key)) {
                pre = (String)pres.get(key);
            }

            return pre + Convert.humpToUnderline(key);
        }
    }

    public CommonSearchDto() {
    }

    public String getSearch() {
        return this.search;
    }

    public PageDto getPage() {
        return this.page;
    }

    public Map<String, Object> getForm() {
        return this.form;
    }

    public Map<String, String> getOp() {
        return this.op;
    }

    public Map<String, String> getCon() {
        return this.con;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setPage(PageDto page) {
        this.page = page;
    }

    public void setForm(Map<String, Object> form) {
        this.form = form;
    }

    public void setOp(Map<String, String> op) {
        this.op = op;
    }

    public void setCon(Map<String, String> con) {
        this.con = con;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommonSearchDto)) {
            return false;
        } else {
            CommonSearchDto<?> other = (CommonSearchDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$search = this.getSearch();
                Object other$search = other.getSearch();
                if (this$search == null) {
                    if (other$search != null) {
                        return false;
                    }
                } else if (!this$search.equals(other$search)) {
                    return false;
                }

                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$form = this.getForm();
                Object other$form = other.getForm();
                if (this$form == null) {
                    if (other$form != null) {
                        return false;
                    }
                } else if (!this$form.equals(other$form)) {
                    return false;
                }

                label62: {
                    Object this$op = this.getOp();
                    Object other$op = other.getOp();
                    if (this$op == null) {
                        if (other$op == null) {
                            break label62;
                        }
                    } else if (this$op.equals(other$op)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$con = this.getCon();
                    Object other$con = other.getCon();
                    if (this$con == null) {
                        if (other$con == null) {
                            break label55;
                        }
                    } else if (this$con.equals(other$con)) {
                        break label55;
                    }

                    return false;
                }

                Object this$orderBy = this.getOrderBy();
                Object other$orderBy = other.getOrderBy();
                if (this$orderBy == null) {
                    if (other$orderBy != null) {
                        return false;
                    }
                } else if (!this$orderBy.equals(other$orderBy)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CommonSearchDto;
    }

//    @Override
//    public int hashCode() {
//        boolean PRIME = true;
//        int result = 1;
//        Object $search = this.getSearch();
//        int result = result * 59 + ($search == null ? 43 : $search.hashCode());
//        Object $page = this.getPage();
//        result = result * 59 + ($page == null ? 43 : $page.hashCode());
//        Object $form = this.getForm();
//        result = result * 59 + ($form == null ? 43 : $form.hashCode());
//        Object $op = this.getOp();
//        result = result * 59 + ($op == null ? 43 : $op.hashCode());
//        Object $con = this.getCon();
//        result = result * 59 + ($con == null ? 43 : $con.hashCode());
//        Object $orderBy = this.getOrderBy();
//        result = result * 59 + ($orderBy == null ? 43 : $orderBy.hashCode());
//        return result;
//    }

    @Override
    public String toString() {
        return "CommonSearchDto(search=" + this.getSearch() + ", page=" + this.getPage() + ", form=" + this.getForm() + ", op=" + this.getOp() + ", con=" + this.getCon() + ", orderBy=" + this.getOrderBy() + ")";
    }
}
