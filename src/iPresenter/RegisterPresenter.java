package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.RegisterEntity;
import iModel.RegisterModel;
import servlet.user.RegisterServlet;

public class RegisterPresenter extends BaseIPresenter<RegisterServlet> {
    RegisterModel registerModel;

    public RegisterPresenter() {
        this.registerModel = new RegisterModel();
    }

    public void doRegister(RegisterEntity registerEntity) {
        registerModel.register(registerEntity, new BaseIModel.ObjectBack() {
            @Override
            public void success(Object obj) {

            }

            @Override
            public void error(int code) {

            }
        });
    }
}
