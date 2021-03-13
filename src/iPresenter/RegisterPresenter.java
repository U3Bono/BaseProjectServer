package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.RegisterModel;
import servlet.user.RegisterServlet;

public class RegisterPresenter extends BaseIPresenter<RegisterServlet> {
    RegisterModel registerModel;

    public RegisterPresenter() {
        this.registerModel = new RegisterModel();
    }

    public void doRegister(UserEntity userEntity) {
        registerModel.register(userEntity, new BaseIModel.ObjectBack() {
            @Override
            public void success(Object obj) {
                view.success(obj);
            }

            @Override
            public void error(int code) {
                view.error(code);
            }
        });
    }
}
