package com.kwdz.blog.api.common.error;

import com.kwdz.blog.api.common.result.ResultModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2007525058641283836L;

    private ResultModel resultModel;

    public DataException(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}