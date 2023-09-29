package models;

import lombok.Data;

@Data
public class AuthResponseModel {

    private String token;
    private String expires;
    private String status;
    private String result;

}
