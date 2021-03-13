package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.RegisterEntity;
import iPresenter.RegisterPresenter;

import static utils.HttpHelper.requestToEntity;

public class RegisterServlet extends BaseServlet<RegisterPresenter, RegisterServlet> implements BaseIView {
    @Override
    protected RegisterPresenter setPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void load() {
        RegisterEntity registerEntity = new RegisterEntity();
        requestToEntity(req, registerEntity);
        iPresenter.doRegister(registerEntity);
    }

    @Override
    public void success(Object obj) {

    }

    @Override
    public void error(int code) {

    }
}
