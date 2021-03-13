package servlet.home;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.WelcomeEntity;
import iPresenter.WelcomePresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static utils.JsonUtils.toJson;
import static utils.ResponseUtils.respBack;


public class WelcomeServlet extends BaseServlet<WelcomePresenter, WelcomeServlet> implements BaseIView {

    @Override
    protected WelcomePresenter setPresenter() {
        return new WelcomePresenter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void success(Object obj) {
        System.out.println(toJson(obj));
        respBack(resp, toJson(obj));
    }

    @Override
    public void error(int code) {

    }
}
