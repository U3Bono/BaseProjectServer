package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.LoginPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static utils.HttpHelper.requestToEntity;

public class LoginServlet extends BaseServlet<LoginPresenter, LoginServlet> implements BaseIView {
    @Override
    protected LoginPresenter setPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        requestToEntity(req, userEntity);
        iPresenter.doLogin(userEntity);
    }

    @Override
    public void success(Object obj) {
        resp.setStatus(200);
    }

    @Override
    public void error(int code) {
        System.out.println(code);
        resp.setStatus(500);
    }

}
