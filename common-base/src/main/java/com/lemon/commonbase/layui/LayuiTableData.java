package com.lemon.commonbase.layui;

import lombok.Data;

import java.util.List;

/**
 * @author dengqiao
 * @date 2019-12-19 15:13
 */
@Data
public class LayuiTableData<T> {
    private int code;
    private String msg;
    private long count;
    private List<T> data;

    public LayuiTableData(long count, List<T> data) {
        this.code = 0;
        this.count = count;
        this.data = data;
    }

    public LayuiTableData(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.count = 0;
    }

}
