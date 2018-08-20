package com.gr.qrapi.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.common.exception.DaoException;
import com.gr.qrapi.core.model.Browsers;
import com.gr.qrapi.core.model.DataList;
import com.gr.qrapi.core.model.Platforms;
import com.gr.qrapi.core.model.QRUrl;
import com.gr.qrapi.core.model.UrlAnalytics;



/**
 * @author Muhammad Mohsin
 */
public class UrlDaoHibernateImpl extends AbstractHibernateDao<QRUrl, Integer> implements UrlDao {
	
	public static UrlDao getDao() {
		return DaoManager.getInstance().getDao(UrlDao.class);
	}

	@Override
	public QRUrl searchById(int id) {
		QRUrl qrurl = null;
		try {
			Session session = getSession();
			qrurl = (QRUrl) session.get(QRUrl.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return qrurl;
	}

	@Override
	public QRUrl addShortUrl(QRUrl qrUrl) {

		Session session = getSession();
		Transaction tx = null;

		try {

			if (qrUrl != null) {

				if (!qrUrl.getOriginalUrl().isEmpty() && !qrUrl.getShortUrl().isEmpty()
						&& qrUrl.getDateCreated() != null && qrUrl.getExpiryDate() != null) {

					tx = session.beginTransaction();
					session.save(qrUrl);
					tx.commit();
				}
			}
		} catch (Exception aex) {
			if (tx != null)
				tx.rollback();
			throw new DaoException(aex);
		}
		return qrUrl;
	}

	@Override
	public QRUrl updateShortUrl(QRUrl qrUrl, int id) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QRUrl> viewAllUrls() {

		List<QRUrl> qrUrls = null;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(QRUrl.class);

			if (criteria != null) {
				qrUrls = (List<QRUrl>) criteria.list();
			}

		} catch (Exception aex) {
			throw new DaoException(aex);
		}
		return qrUrls;
	}

	@Override
	public QRUrl searchByShortUrl(String shorturl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalClicks(int id) {
		int grandSumClicks = 0;
		try {
			Session session = getSession();
			QRUrl qrurl = (QRUrl) session.get(QRUrl.class, id);
			grandSumClicks = qrurl.getGrandSum();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return grandSumClicks;
	}

	@SuppressWarnings("null")
	@Override
	public DataList getPlatformClicks(int id) {
		
		DataList dataList = new DataList();
		
		try {

			Session session = getSession();
			QRUrl qrurl = (QRUrl) session.get(QRUrl.class, id);
			List<UrlAnalytics> byYearList = qrurl.getUrlAnalytics();

			int clickValues[] = new int[6];
			String clickLabels[] = { "Windows", "Android", "IPhone", "Mac", "X11", "Others" };

			for (UrlAnalytics eachYear : byYearList) {

				Platforms platforms = (Platforms) eachYear.getPlatforms().get(0);

				clickValues[0] += platforms.getWindowsClicks();
				clickValues[1] += platforms.getAndroidClicks();
				clickValues[2] += platforms.getIphoneClicks();
				clickValues[3] += platforms.getMacClicks();
				clickValues[4] += platforms.getX11Clicks();
				clickValues[5] += platforms.getOtherClicks();

			}

			List<String> labelList = new ArrayList<String>();
			List<Integer> valueList = new ArrayList<Integer>();

			for (int i = 0; i < clickLabels.length; i++) {
				labelList.add(clickLabels[i]);
				valueList.add(clickValues[i]);
			}

			dataList.setClickLabels(labelList);
			dataList.setClickValues(valueList);

		} catch (Exception e) {
			throw new DaoException(e);
		}
		return dataList;
	}

	@SuppressWarnings("null")
	@Override
	public DataList getBrowserClicks(int id) {

		DataList dataList = new DataList();
		try {

			Session session = getSession();
			QRUrl qrurl = (QRUrl) session.get(QRUrl.class, id);
			List<UrlAnalytics> byYearList = qrurl.getUrlAnalytics();

			int clickValues[] = new int[5];
			String clickLabels[] = { "Internet Explorer", "Chrome", "Safari", "FireFox", "Others" };

			for (UrlAnalytics eachYear : byYearList) {

				Browsers browsers = (Browsers) eachYear.getBrowsers().get(0);

				clickValues[0] += browsers.getIeClicks(); 
				clickValues[1] += browsers.getChromeclicks();
				clickValues[2] += browsers.getSafariClicks();
				clickValues[3] += browsers.getFireforxClicks();
				clickValues[4] += browsers.getOthersClicks();

			}

			List<String> labelList = new ArrayList<String>();
			List<Integer> valueList = new ArrayList<Integer>();

			for (int i = 0; i < clickLabels.length; i++) {
				labelList.add(clickLabels[i]);
				valueList.add(clickValues[i]);
			}

			dataList.setClickLabels(labelList);
			dataList.setClickValues(valueList);

		} catch (Exception e) {
			throw new DaoException(e);
		}
		return dataList;
	}

	@SuppressWarnings("null")
	@Override
	public DataList getByYearClicks(int id) {
		
		DataList dataList = new DataList();
		List<String> labelList = new ArrayList<String>();
		List<Integer> valueList = new ArrayList<Integer>();
		
		try {

			Session session = getSession();
			QRUrl qrurl = (QRUrl) session.get(QRUrl.class, id);
			List<UrlAnalytics> byYearList = qrurl.getUrlAnalytics();

			int clickValues[] = new int[byYearList.size()];
			String clickLabels[] = new String[byYearList.size()];

			int j = 0;
			for (UrlAnalytics eachYear : byYearList) {
				
				clickLabels[j] = eachYear.getYearOfClicks().toString();
				clickValues[j] = eachYear.getTotalClicksCount();

				j++;
			}

			for (int k = 0; k < byYearList.size(); k++) { 
				labelList.add(clickLabels[k]);
				valueList.add(clickValues[k]);
			}

			dataList.setClickLabels(labelList);
			dataList.setClickValues(valueList);

		} catch (Exception e) {
			throw new DaoException(e);
		}
		return dataList;
	}
	
}
