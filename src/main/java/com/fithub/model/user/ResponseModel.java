package com.fithub.model.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class ResponseModel {
    private boolean status = false;
    private String token = "";
    private String username = "";
    private Integer loa = 0;
    
    public String toJSONString(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(this);
        }catch (Exception exception){
            try {
                throw exception;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
}
