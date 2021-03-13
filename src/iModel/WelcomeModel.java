package iModel;

import base.basMVP.BaseIModel;
import entity.WelcomeEntity;

import java.util.HashMap;
import java.util.Map;

public class WelcomeModel {

    public void welcome(WelcomeEntity welcomeEntity, BaseIModel.ObjectBack objectBack){
        Map<String, Object> map = new HashMap<>();
        map.put("test1", "welcome");
        map.put("test2", "to");
        map.put("test3", 1);
        objectBack.success(map);
    }
}
