package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.WelcomeEntity;
import iModel.WelcomeModel;
import servlet.home.WelcomeServlet;

public class WelcomePresenter extends BaseIPresenter<WelcomeServlet> {

    WelcomeModel welcomeModel;

    public WelcomePresenter() {
        this.welcomeModel = new WelcomeModel();
    }

    public void getWelcome(WelcomeEntity welcomeEntity) {
        if (view == null | welcomeModel == null)
            return;
        welcomeModel.welcome(welcomeEntity, new BaseIModel.ObjectBack() {
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
