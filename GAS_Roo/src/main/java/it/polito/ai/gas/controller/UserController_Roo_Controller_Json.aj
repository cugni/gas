// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;
import it.polito.ai.gas.controller.UserController;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

privileged aspect UserController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.showJson(@PathVariable("id") Integer id) {
        User user = User.findUser(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (user == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<User> result = User.findAllUsers();
        return new ResponseEntity<String>(User.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> UserController.createFromJson(@RequestBody String json) {
        User user = User.fromJsonToUser(json);
        user.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> UserController.createFromJsonArray(@RequestBody String json) {
        for (User user: User.fromJsonArrayToUsers(json)) {
            user.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> UserController.updateFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        User user = User.fromJsonToUser(json);
        if (user.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> UserController.updateFromJsonArray(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        for (User user: User.fromJsonArrayToUsers(json)) {
            if (user.merge() == null) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> UserController.deleteFromJson(@PathVariable("id") Integer id) {
        User user = User.findUser(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (user == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        user.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByApprovedNot", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.jsonFindUsersByApprovedNot(@RequestParam(value = "approved", required = false) Boolean approved) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(User.toJsonArray(User.findUsersByApprovedNot(approved == null ? Boolean.FALSE : approved).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByAuthTokenEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.jsonFindUsersByAuthTokenEquals(@RequestParam("authToken") String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(User.toJsonArray(User.findUsersByAuthTokenEquals(authToken).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByRole", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.jsonFindUsersByRole(@RequestParam("role") UserType role) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(User.toJsonArray(User.findUsersByRole(role).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByUsernameEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> UserController.jsonFindUsersByUsernameEquals(@RequestParam("username") String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(User.toJsonArray(User.findUsersByUsernameEquals(username).getResultList()), headers, HttpStatus.OK);
    }
    
}
