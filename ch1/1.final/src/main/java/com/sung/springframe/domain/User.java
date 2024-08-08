// User 클래스 - 사용자를 표현하는 클래스
package com.sung.springframe.domain;

public class User {
    // 사용자 ID, 이름, 비밀번호를 저장할 변수
    String id;
    String name;
    String password;
    
    // ID를 가져오는 메소드
    public String getId() {
        return id;
    }
    // ID를 설정하는 메소드
    public void setId(String id) {
        this.id = id;
    }
    // 이름을 가져오는 메소드
    public String getName() {
        return name;
    }
    // 이름을 설정하는 메소드
    public void setName(String name) {
        this.name = name;
    }
    // 비밀번호를 가져오는 메소드
    public String getPassword() {
        return password;
    }
    // 비밀번호를 설정하는 메소드
    public void setPassword(String password) {
        this.password = password;
    }
}
