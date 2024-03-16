/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import utils.Pagination;

/**
 *
 * @author minhh
 * @param <T>
 * Generic data
 */
public class PagingModel<T> {

    private int totalPage = 0;
    private int totalRecord;
    private List<T> data;

    public PagingModel() {
    }

    public int getTotalPage() {
        return totalRecord % Pagination.RECORD_PER_PAGE == 0
                ? (totalRecord / Pagination.RECORD_PER_PAGE)
                : (totalRecord / Pagination.RECORD_PER_PAGE) + 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
