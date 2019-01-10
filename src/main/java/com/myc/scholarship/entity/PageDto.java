package com.myc.scholarship.entity;

import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2018/12/1$ 14:40$
 */
@Data
public class PageDto {
    private Integer current;
    private Integer pageCount;

    public PageDto() {
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageDto)) {
            return false;
        } else {
            PageDto other = (PageDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$current = this.getCurrent();
                Object other$current = other.getCurrent();
                if (this$current == null) {
                    if (other$current != null) {
                        return false;
                    }
                } else if (!this$current.equals(other$current)) {
                    return false;
                }

                Object this$pageCount = this.getPageCount();
                Object other$pageCount = other.getPageCount();
                if (this$pageCount == null) {
                    if (other$pageCount != null) {
                        return false;
                    }
                } else if (!this$pageCount.equals(other$pageCount)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageDto;
    }

//    @Override
//    public int hashCode() {
//        boolean PRIME = true;
//        int result = 1;
//        Object $current = this.getCurrent();
//        int result = result * 59 + ($current == null ? 43 : $current.hashCode());
//        Object $pageCount = this.getPageCount();
//        result = result * 59 + ($pageCount == null ? 43 : $pageCount.hashCode());
//        return result;
//    }

    @Override
    public String toString() {
        return "PageDto(current=" + this.getCurrent() + ", pageCount=" + this.getPageCount() + ")";
    }
}
