package com.kwdz.blog.api.common.error;

import com.kwdz.blog.api.common.result.ResultModel;

import java.io.Serializable;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/28 17:09
 */
public class AccountException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1725500108048403621L;

    private ResultModel resultModel;

    public AccountException(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}
