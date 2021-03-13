package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.LoginEntity;
import iPresenter.LoginPresenter;

import static utils.HttpHelper.requestToEntity;

public class LoginServlet extends BaseServlet<LoginPresenter, LoginServlet> implements BaseIView {
    @Override
    protected LoginPresenter setPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void load() {
        LoginEntity loginEntity = new LoginEntity();
        requestToEntity(req, loginEntity);
        iPresenter.doLogin(loginEntity);
    }

    @Override
    public void success(Object obj) {
        resp.setStatus(200);
    }

    @Override
    public void error(int code) {
        resp.setStatus(500);
    }

}
