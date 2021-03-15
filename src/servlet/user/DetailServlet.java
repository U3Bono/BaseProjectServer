package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.DetailPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.HttpHelper;

import java.io.IOException;

public class DetailServlet extends BaseServlet<DetailPresenter, DetailServlet> implements BaseIView {
    @Override
    protected DetailPresenter setPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        HttpHelper.requestToEntity(req, userEntity);
        iPresenter.getDetail(userEntity);
    }

}
