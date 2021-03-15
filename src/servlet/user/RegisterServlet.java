package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.RegisterPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.HttpHelper;

import java.io.IOException;

public class RegisterServlet extends BaseServlet<RegisterPresenter, RegisterServlet> implements BaseIView {
    @Override
    protected RegisterPresenter setPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        HttpHelper.requestToEntity(req, userEntity);
        iPresenter.doRegister(userEntity);
    }

}
