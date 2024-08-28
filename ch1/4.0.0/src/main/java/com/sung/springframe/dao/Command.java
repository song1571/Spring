package com.sung.springframe.dao;

import java.util.Map;

//Command interface
interface Command {
 void setState(Map<String, Object> state);
 Object execute();
 
}
