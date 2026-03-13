package com.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {
    @WebMethod
    String createUser(int id, String username, String password);

    @WebMethod
    String retrieveUser(int id);

    @WebMethod
    String updateUser(int id, String username, String password);

    @WebMethod
    String deleteUser(int id);
}