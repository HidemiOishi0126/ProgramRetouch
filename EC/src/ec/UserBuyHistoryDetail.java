package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String buyIdst = request.getParameter("buy_id");
		int buyId = Integer.parseInt(buyIdst);

		BuyDAO buyDao = new BuyDAO();
		BuyDataBeans buyData = null;
		try {
			buyData = buyDao.getBuyDataBeansByBuyId(buyId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("buyData", buyData);





		BuyDetailDAO buyDetailDao = new BuyDetailDAO();
		ArrayList<ItemDataBeans> buyDetailItemList = null;
		try {
			buyDetailItemList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("buyDetailItemList", buyDetailItemList);

		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}
}
