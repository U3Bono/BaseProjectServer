package base;

import base.basMVP.BaseIPresenter;
import base.basMVP.BaseIView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public abstract class BaseServlet<T extends BaseIPresenter<V>, V extends BaseIView> extends HttpServlet {

    protected T iPresenter;
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    @Override
    public void init() throws ServletException {
        iPresenter = setPresenter();
        iPresenter.attachView((V) this);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        load();
    }

    @Override
    public void destroy() {
        if (iPresenter != null) {
            iPresenter.detachView();
            iPresenter = null;
        }
    }

    protected abstract T setPresenter();

    protected abstract void load();

}
