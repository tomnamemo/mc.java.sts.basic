package com.mc.basic.infra.response;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) //null은 걸러줌
public record ApiResponse<T>(
        String code,
        String message,
        T Data
) {
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(ResponseCode.OK.code(), ResponseCode.OK.message(),data);
    }

    public static <T> ApiResponse<T> noContent(){
        return new ApiResponse<>(ResponseCode.OK.code(), ResponseCode.OK.message(),null);
    }

    public static <T> ApiResponse<T> error(ResponseCode code){
        return new ApiResponse<>(code.code(), code.message(),null);
    }

}
