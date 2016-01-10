package com.demo.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.demo.struts.util.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.sun.istack.internal.logging.Logger;

import com.demo.hibernate.beans.UserProfile;
import com.demo.hibernate.beans.BeanUtil;

public class ModifyProfileAction extends DispatchAction {

	Mongo mongo = null;

	Logger log = Logger.getLogger(this.getClass());

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);

		try {
			String username = (String) session.getAttribute("username");

			// connect to mongodb
			if (mongo == null) {
				mongo = new Mongo("127.0.0.1", 27017);
			}
			DB db = mongo.getDB("mydb");
			db.authenticate("uid", "pwd".toCharArray());
			DBCollection coll = db.getCollection("user");
			BasicDBObject filter_dbobject = new BasicDBObject();
			filter_dbobject.put("name", username);
			DBObject object = coll.findOne(filter_dbobject);
			UserProfile profile = new UserProfile();

			session.setAttribute("username", username);
			if (object != null) {
				profile = BeanUtil.dbObject2Bean(object, profile);
				session.setAttribute("telephone", profile.getTelephone());
				session.setAttribute("address", profile.getAddress());
				session.setAttribute("user_info", profile.getDescription());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		try {
			// get Parameters
			String username = (String) session.getAttribute("username");
			String telephone = (String) request.getParameter("telephone");
			String address = (String) request.getParameter("address");
			String description = (String) request.getParameter("user_info");

			// connect db
			if (mongo == null) {
				mongo = new Mongo("127.0.0.1", 27017);
			}
			DB db = mongo.getDB("mydb");
			db.authenticate("uid", "pwd".toCharArray());
			DBCollection coll = db.getCollection("user");

			// Check existence
			BasicDBObject filter_dbobject = new BasicDBObject();
			filter_dbobject.put("name", username);
			DBObject object = coll.findOne(filter_dbobject);
			if (object == null) {
				BasicDBObject doc = new BasicDBObject();
				doc.put("name", username);
				coll.insert(doc);
			}

			// Update profile
			DBObject updateCondition = new BasicDBObject();
			updateCondition.put("name", username);
			DBObject updatedValue = new BasicDBObject();
			updatedValue.put("telephone", telephone);
			updatedValue.put("address", address);
			updatedValue.put("description", description);
			DBObject updateSetValue = new BasicDBObject("$set", updatedValue);
			coll.update(updateCondition, updateSetValue);

			// query
			filter_dbobject = new BasicDBObject();
			filter_dbobject.put("name", username);
			object = coll.findOne(filter_dbobject);
			UserProfile profile = new UserProfile();
			profile = BeanUtil.dbObject2Bean(object, profile);

			// set attribute
			session.setAttribute("username", username);
			session.setAttribute("telephone", profile.getTelephone());
			session.setAttribute("address", profile.getAddress());
			session.setAttribute("user_info", profile.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}
}
